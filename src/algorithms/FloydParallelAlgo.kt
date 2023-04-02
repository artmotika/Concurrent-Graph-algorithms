package algorithms

import `threads-pool`
import model.Graph
import java.util.*
import java.util.concurrent.Executors
import kotlin.math.*

private var `threads-num`: Int = 2

fun calculateChunksLength(numVerticies: Int): Array<Int> {
    val arrayChunksLength = Array (`threads-num`+1) {0}
    val length = numVerticies / `threads-num`
    val remainder = numVerticies % `threads-num`
    if ( remainder == 0) {
        for (i in 1..`threads-num`) {
            arrayChunksLength[i] = length
        }
    } else if (numVerticies < `threads-num`) {
        arrayChunksLength[1] = numVerticies
    } else {
        for (i in 1..`threads-num`) {
            arrayChunksLength[i] = length
        }
        arrayChunksLength[1] += remainder
    }
    return arrayChunksLength
}

fun calculateChunksLeftBorders(chunksLength: Array<Int>): Array<Int> {
    var memLeftBorder = Array(`threads-num`) {0}
    for (i in 1 until `threads-num`) {
        for (j in 1..i) {
            memLeftBorder[i] += chunksLength[j]
        }
    }
    return memLeftBorder
}

fun startFloydParallel(graph: Graph): Array<Array<Double>> {
    val vertices = graph.vertices
    val edges = graph.edges
    val numVertices = vertices.size
    val distances = Array(numVertices) { Array(numVertices) {Double.POSITIVE_INFINITY} }
    for ( i in 0 until numVertices) {
        for (j in 0 until numVertices) {
            if (i == j) {
                distances[i][j] = 0.0
            } else {
                checkInEdges(i, j, edges, distances)
            }
        }
    }

    val chunksLength = calculateChunksLength(numVertices)
    val leftBorders = calculateChunksLeftBorders(chunksLength)
    val threadPool = Executors.newFixedThreadPool(`threads-pool`)
    for (k in 0 until numVertices) {
        for (t in 1..`threads-num`) {
            for (i in 0 + leftBorders[t - 1] until leftBorders[t - 1] + chunksLength[t]) {
                for (c in 1..`threads-num`) {
                    val task = Runnable {
                        for (j in 0 + leftBorders[c - 1] until leftBorders[c - 1] + chunksLength[c]) {
                            distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
                        }
                    }
                    threadPool.execute(task)
                }
            }
        }
    }

    threadPool.shutdown()
    while (!threadPool.isTerminated) {}
    return distances
}
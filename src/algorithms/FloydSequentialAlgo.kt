package algorithms
import model.*
import java.awt.DisplayMode
import java.util.*
import kotlin.math.min

fun checkInEdges(id1: Int, id2: Int, edges: List<Edge>, distances: Array<Array<Double>>) {
    edges.forEach { e ->
        if ((e.start.id == id1) && (e.end.id == id2)) {
            distances[id1][id2] = e.weight
        } else if ((e.start.id == id2) && (e.end.id == id1)) {
            distances[id2][id1] = e.weight
        }
    }
}

fun startFloydSequential(graph: Graph): Array<Array<Double>>{
    val vertices = graph.vertices
    val edges = graph.edges
    val num_vertices = vertices.size
    val distances = Array(num_vertices) { Array(num_vertices) {Double.POSITIVE_INFINITY} }
    for ( i in 0 until num_vertices) {
        for (j in 0 until num_vertices) {
            if (i == j) {
                distances[i][j] = 0.0
            } else {
                checkInEdges(i, j, edges, distances)
            }
        }
    }
    for ( k in 0 until num_vertices) {
        for ( i in 0 until num_vertices) {
            for ( j in 0 until num_vertices) {
                distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
            }
        }
    }

    return distances
}
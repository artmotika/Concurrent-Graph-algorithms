import model.*
import algorithms.*
import java.util.PriorityQueue
import kotlin.math.*

fun compare2DArrays(array1: Array<Array<Double>>, array2: Array<Array<Double>>, eps: Double, length: Int): Boolean {
    for (i in 0 until length) {
        for (j in 0 until length) {
            if (abs(array1[i][j] - array2[i][j]) > eps) return false
        }
    }
    return true
}

fun compareArrays(array1: Array<Double>, array2: Array<Double>, eps: Double, length: Int): Boolean {
    for (i in 0 until length) {
        if (abs(array1[i] - array2[i]) > eps) return false
    }
    return true
}

fun main() {
    var t1: Long
    var t2: Long


    val graph = Graph()

    print("Введите кол-во вершин в графе: ")
    val num_vertices = readLine()!!.toInt()


    for (i in 0..num_vertices) {
        graph.addVertex(i)
    }
    repeat((num_vertices/20..num_vertices*100).random()) {
        graph.addEdge((0..num_vertices).random(), (0..num_vertices).random(), (0..100).random().toDouble())
    }

    val verticiesMap = graph.verticesMap
    val eps = 0.000001

    t1 = System.nanoTime()
    val distance1 = startDijkstraSequential(graph, verticiesMap[1]!!)
    t2 = System.nanoTime()
    println("Time of Dijkstra Sequential Algorithm: ${(t2 - t1).toDouble() / 1000000000}")

    t1 = System.nanoTime()
    val distance2 = startDijkstraParallel(graph, verticiesMap[1]!!)
    t2 = System.nanoTime()
    println("Time of Dijkstra Parallel Algorithm: ${(t2 - t1).toDouble() / 1000000000}")

    if (!compareArrays(distance1, distance2, eps, num_vertices))
        println("Error!!! sequential dijkstra is not equal to parallel dijkstra")


    t1 = System.nanoTime()
    val distances1 = startFloydSequential(graph)
    t2 = System.nanoTime()
    println("Time of Floyd Sequential Algorithm: ${(t2 - t1).toDouble() / 1000000000}")

    t1 = System.nanoTime()
    val distances2 = startFloydParallel(graph)
    t2 = System.nanoTime()
    println("Time of Floyd Parallel Algorithm: ${(t2 - t1).toDouble() / 1000000000}")

    if (!compare2DArrays(distances1, distances2, eps, num_vertices))
        println("Error!!! sequential floyd is not equal to parallel floyd")
}
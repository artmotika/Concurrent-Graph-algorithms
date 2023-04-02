package tests.kotlin
import algorithms.startDijkstraParallel
import model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs



class testDijkstraParallel {
    private var graph = Graph()
    private fun createGraph(graph: Graph) {
        val num_vertices = 6
        for (i in 0..num_vertices) {
            graph.addVertex(i)
        }
        graph.addEdge( 0, 1, 4.0)
        graph.addEdge( 0, 2, 2.0)
        graph.addEdge( 0, 5, 7.0)
        graph.addEdge( 1, 0, 4.0)
        graph.addEdge( 1, 3, 2.0)
        graph.addEdge( 2, 0, 2.0)
        graph.addEdge( 2, 4, 8.0)
        graph.addEdge( 2, 5, 3.0)
        graph.addEdge( 3, 1, 2.0)
        graph.addEdge( 3, 5, 5.0)
        graph.addEdge( 3, 6, 6.0)
        graph.addEdge( 4, 2, 8.0)
        graph.addEdge( 4, 1, 3.0)
        graph.addEdge( 5, 0, 7.0)
        graph.addEdge( 5, 2, 3.0)
    }

    private fun compareArrays(array1: Array<Double>, array2: Array<Double>, length: Int): Boolean {
        for (i in 0 until length) {
            if (array1[i] != array2[i]) return false
        }
        return true
    }


    @BeforeEach
    fun makeTree() {
        graph = Graph()
    }

    @Test
    fun `check on usual graph`() {
        var result = true
        createGraph(graph)
        val num_vertices = graph.vertices.size-1
        val verticiesMap = graph.verticesMap
        val distances = Array(num_vertices){Array(num_vertices) {0.0}}
        distances[0] = arrayOf(0.0, 4.0, 2.0, 6.0, 10.0, 5.0)
        distances[1] = arrayOf(4.0, 0.0, 6.0, 2.0, 14.0, 7.0)
        distances[2] = arrayOf(2.0, 6.0, 0.0, 8.0, 8.0, 3.0)
        distances[3] = arrayOf(6.0, 2.0, 8.0, 0.0, 16.0, 5.0)
        distances[4] = arrayOf(7.0, 3.0, 8.0, 5.0, 0.0, 10.0)
        distances[5] = arrayOf(5.0, 9.0, 3.0, 11.0, 11.0, 0.0)
        for (i in 0 until num_vertices) {
            if (!compareArrays(startDijkstraParallel(graph, verticiesMap[i]!!), distances[i], num_vertices)) {
                result = false
                break
            }
        }
        assertTrue(result)
    }

    @Test
    fun `check on graph with one vertex`() {
        graph.addVertex(0)
        graph.addEdge(0, 0, 2.0)
        val verticiesMap = graph.verticesMap
        assertEquals(startDijkstraParallel(graph, verticiesMap[0]!!)[0], 0.0)
    }

    @Test
    fun `check on graph without edges`() {
        var result = true
        val num_vertices = 6
        for (i in 0..num_vertices) {
            graph.addVertex(i)
        }
        val verticiesMap = graph.verticesMap
        val distances = Array(num_vertices){Array(num_vertices) {Double.POSITIVE_INFINITY}}
        for (i in 0 until num_vertices) {
            for (j in 0 until num_vertices) {
                if (i == j) distances[i][j] = 0.0
            }
        }

        for (i in 0 until num_vertices) {
            if (!compareArrays(startDijkstraParallel(graph, verticiesMap[i]!!), distances[i], num_vertices)) {
                result = false
                break
            }
        }
        assertTrue(result)
    }

}
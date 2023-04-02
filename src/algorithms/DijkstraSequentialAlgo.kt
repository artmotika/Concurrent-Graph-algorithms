package algorithms
import model.Graph
import model.Vertex
import java.util.*

fun startDijkstraSequential(graph: Graph, start: Vertex): Array<Double> {
    val vertices = graph.vertices
    val edges = graph.edges
    val num_vertices = vertices.size
    val distance = Array (num_vertices) {Double.POSITIVE_INFINITY}
    distance[start.id] = 0.0

    val Q = PriorityQueue<Pair<Double, Int>>(compareBy { it.first })
    Q.add(Pair(0.0, start.id))

    while (!Q.isEmpty()) {
        val c: Pair<Double, Int> = Q.peek()
        Q.remove()
        val dist: Double = c.first
        val v: Int = c.second

        if (distance[v] < dist) {
            continue
        }
        edges.forEach { e ->
            if ((e.start.id == v)) {
                val u: Int = e.end.id
                val n_dist: Double = dist + e.weight
                if (n_dist < distance[u]) {
                    distance[u] = n_dist
                    Q.add(Pair(n_dist, u))
                }
            }
        }
    }
    return distance
}
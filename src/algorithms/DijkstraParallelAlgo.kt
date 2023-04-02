package algorithms
import model.*
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread


fun addInMPQ(MultyPriorityQueue: Array<PriorityBlockingQueue<Pair<Double, Int>>>, LenOfArray: Int,
                            el: Pair<Double, Int>) {

    val i = (0 until LenOfArray).random()
    MultyPriorityQueue[i].add(el)
}

@Synchronized
fun removeInMPQ(MultyPriorityQueue: Array<PriorityBlockingQueue<Pair<Double, Int>>>, LenOfArray: Int):
        Pair<Double, Int>? {
    while (true) {
        val i = (0 until LenOfArray).random()
        val j = (0 until LenOfArray).random()
        if (i == j || (MultyPriorityQueue[i].peek() == null && MultyPriorityQueue[j].peek() == null)) {
            return null
        }else if (MultyPriorityQueue[i].peek() != null && MultyPriorityQueue[j].peek() != null) {
            if (MultyPriorityQueue[i].peek()!!.first < MultyPriorityQueue[j].peek()!!.first) {
                return MultyPriorityQueue[i].poll()
            } else {
                return MultyPriorityQueue[j].poll()
            }
        }else if (MultyPriorityQueue[i].peek() != null) {
            return MultyPriorityQueue[i].poll()
        }else {
            return MultyPriorityQueue[j].poll()
        }
    }

}

@Synchronized
fun compareAndSet(distance: Array<Double>, v: Int, old: Double, new: Double): Boolean {
    if (distance[v] <= old) return false
    distance[v] = new
    return true
}


fun startDijkstraParallel(graph: Graph, start: Vertex): Array<Double> {
    val numberThreads = Runtime.getRuntime().availableProcessors()
    val `parallel-queue-size` = numberThreads*2
    val vertices = graph.vertices
    val edges = graph.edges
    val num_vertices = vertices.size
    val distance = Array (num_vertices) {Double.POSITIVE_INFINITY}
    distance[start.id] = 0.0


    val Q: PriorityBlockingQueue<Pair<Double, Int>> = PriorityBlockingQueue<Pair<Double, Int>>(num_vertices ,compareBy { it.first })
    val MultyPriorityQueue: Array<PriorityBlockingQueue<Pair<Double, Int>>> = Array(`parallel-queue-size`, {Q})
    for (i in MultyPriorityQueue.indices) {
        MultyPriorityQueue[i] = PriorityBlockingQueue<Pair<Double, Int>>(num_vertices, compareBy { it.first })
    }

    addInMPQ(MultyPriorityQueue, `parallel-queue-size`, Pair(0.0, start.id))
    val activeNodes = AtomicInteger(1)
    List(numberThreads) {
        thread {
            loop@ while (activeNodes.get() > 0) {
                val (dist, v) = removeInMPQ(MultyPriorityQueue, `parallel-queue-size`) ?: continue@loop

                if (distance[v] < dist) {
                    activeNodes.decrementAndGet()
                    continue@loop
                }

                edges.forEach { e ->
                    if ((e.start.id == v)) {

                        val u: Int = e.end.id
                        val n_dist: Double = dist + e.weight


                        if (compareAndSet(distance, u, n_dist, n_dist)) {
                            addInMPQ(MultyPriorityQueue, `parallel-queue-size`, Pair(n_dist, u))
                            activeNodes.incrementAndGet()
                        }
                    }
                }
                activeNodes.decrementAndGet()

            }
        }
    }.forEach(Thread::join)

    return distance
}

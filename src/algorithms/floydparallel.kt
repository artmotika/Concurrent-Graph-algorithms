import algorithms.checkInEdges
import model.Graph
import java.util.concurrent.Executors
import kotlin.math.min

//package algorithms
//
//import model.Graph
//import java.util.*
//import java.util.concurrent.Executors
//import java.util.concurrent.TimeUnit
//import kotlin.math.*
//import `threads-pool`
//import com.sun.source.tree.ArrayAccessTree
//import java.util.concurrent.locks.ReentrantLock



//fun calculateChunksLength(numVerticies: Int): Array<Int> {
//    val arrayChunksLength = Array (`threads-pool`+1) {0}
//    val length = numVerticies / `threads-pool`
//    val remainder = numVerticies % `threads-pool`
//    if ( remainder == 0) {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//    } else if (numVerticies < `threads-pool`) {
//        arrayChunksLength[1] = numVerticies
//    } else {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//        arrayChunksLength[1] += remainder
//    }
//    return arrayChunksLength
//}
//
//fun calculateChunksLeftBorders(chunksLength: Array<Int>): Array<Int> {
//    var memLeftBorder = Array(`threads-pool`) {0}
//    for (i in 1 until `threads-pool`) {
//        for (j in 1..i) {
//            memLeftBorder[i] += chunksLength[j]
//        }
//    }
//    return memLeftBorder
//}
//
//fun startFloydParallel(graph: Graph): Array<Array<Double>> {
//    val vertices = graph.vertices
//    val edges = graph.edges
//    val numVertices = vertices.size
//    val distances = Array(numVertices) { Array(numVertices) {Double.POSITIVE_INFINITY} }
//    for ( i in 0 until numVertices) {
//        for (j in 0 until numVertices) {
//            if (i == j) {
//                distances[i][j] = 0.0
//            } else {
//                checkInEdges(i, j, edges, distances)
//            }
//        }
//    }
//
//    val chunksLength = calculateChunksLength(numVertices)
//    val leftBorders = calculateChunksLeftBorders(chunksLength)
//    val threadPool = Executors.newFixedThreadPool(`threads-pool`)
//    for (k in 0 until numVertices) {
//        for (t in 1..`threads-pool`) {
//            for (i in 0 + leftBorders[t - 1] until leftBorders[t - 1] + chunksLength[t]) {
//                for (c in 1..`threads-pool`) {
//                    val task = Runnable {
//                        for (j in 0 + leftBorders[c - 1] until leftBorders[c - 1] + chunksLength[c]) {
//                            distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
//                        }
//                    }
//                    threadPool.execute(task)
//                }
//            }
//        }
////        for (t in 1..`threads-pool`) {
////            val task = Runnable {
////                for (i in 0 + leftBorders[t - 1] until leftBorders[t - 1] + chunksLength[t]) {
////                    for (j in 0 until numVertices) {
////                        distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
////                    }
////                }
////            }
////            threadPool.execute(task)
////        }
//    }
//
//    threadPool.shutdown()
//    while (!threadPool.isTerminated) {}
//    return distances
//}


//fun calculateChunksLength(numVerticies: Int): Array<Int> {
//    val arrayChunksLength = Array (`threads-pool`+1) {0}
//    val length = numVerticies / `threads-pool`
//    val remainder = numVerticies % `threads-pool`
//    if ( remainder == 0) {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//    } else if (numVerticies < `threads-pool`) {
//        arrayChunksLength[1] = numVerticies
//    } else {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//        arrayChunksLength[1] += remainder
//    }
//    return arrayChunksLength
//}
//
//fun calculateChunksLeftBorders(chunksLength: Array<Int>): Array<Int> {
//    var memLeftBorder = Array(`threads-pool`) {0}
//    for (i in 1 until `threads-pool`) {
//        for (j in 1..i) {
//            memLeftBorder[i] += chunksLength[j]
//        }
//    }
//    return memLeftBorder
//}



//fun startFloydParallel(graph: Graph): Array<Array<Double>> {
//    val vertices = graph.vertices
//    val edges = graph.edges
//    val numVertices = vertices.size
//    val distances = Array(numVertices) { Array(numVertices) {Double.POSITIVE_INFINITY} }
//    for ( i in 0 until numVertices) {
//        for (j in 0 until numVertices) {
//            if (i == j) {
//                distances[i][j] = 0.0
//            } else {
//                checkInEdges(i, j, edges, distances)
//            }
//        }
//    }
//
//    val threadPool = Executors.newFixedThreadPool(`threads-pool`)
//    val chunksLength = calculateChunksLength(numVertices)
//    val leftBorders = calculateChunksLeftBorders(chunksLength)
//    val kRow = Array(numVertices) {0.0}
//
//
//    for (k in 0 until numVertices) {
//        for (t in 1..`threads-pool`) {
//            val task = Runnable {
////                val lock = Any()
////                synchronized(lock) {
////                    for (i in 0 until numVertices) {
////                        kRow[i] = distances[k][i]
////                    }
////                }
//                for (i in 0 + leftBorders[t-1] until leftBorders[t-1] + chunksLength[t]) {
//                    val lengthFromItoK = distances[i][k]
//                    for (j in 0 until numVertices) {
//                        //distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
//                        distances[i][j] = min(distances[i][j], lengthFromItoK + kRow[j])
//                    }
//                }
//            }
//            threadPool.execute(task)
//        }
//    }
//    threadPool.shutdown()
//    while (!threadPool.isTerminated) {}
//    return distances
//}

//fun startFloydParallel(graph: Graph): Array<Array<Double>> {
//    val vertices = graph.vertices
//    val edges = graph.edges
//    val numVertices = vertices.size
//    val distances = Array(numVertices) { Array(numVertices) {Double.POSITIVE_INFINITY} }
//    for ( i in 0 until numVertices) {
//        for (j in 0 until numVertices) {
//            if (i == j) {
//                distances[i][j] = 0.0
//            } else {
//                checkInEdges(i, j, edges, distances)
//            }
//        }
//    }
//
//    val threadPool = Executors.newFixedThreadPool(`threads-pool`)
//    val chunksLength = calculateChunksLength(numVertices)
//    val leftBorders = calculateChunksLeftBorders(chunksLength)
//    val kRow = Array(numVertices) {0.0}
//
//
//    for (k in 0 until numVertices) {
//        for (t in 1..`threads-pool`) {
//            //val task = Runnable {
//                val lock = Any()
//                synchronized(lock) {
//                    for (i in 0 until numVertices) {
//                        kRow[i] = distances[k][i]
//                    }
//                }
//                for (i in 0 + leftBorders[t-1] until leftBorders[t-1] + chunksLength[t]) {
//                    //val lengthFromItoK = distances[i][k]
//                    for (c in 1..`threads-pool`) {
//                        val task = Runnable {
//                            for (j in 0 + leftBorders[c - 1] until leftBorders[c - 1] + chunksLength[c]) {
//                                //distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
//                                distances[i][j] = min(distances[i][j], distances[i][k] + kRow[j])
//                            }
//                        }
//                        threadPool.execute(task)
//                    }
//                }
////                for (i in 0 + leftBorders[t-1] until leftBorders[t-1] + chunksLength[t]) {
////                    val lengthFromItoK = distances[i][k]
////                    for (j in 0 + leftBorders[t-1] until leftBorders[t-1] + chunksLength[t]) {
////                        //distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
////                        distances[i][j] = min(distances[i][j], lengthFromItoK + kRow[j])
////                    }
////                }
//            //}
//            //threadPool.execute(task)
//        }
//    }
//    threadPool.shutdown()
//    while (!threadPool.isTerminated) {}
//    return distances
//}






//
//fun calculateChunksLength(numVerticies: Int): Array<Int> {
//    val arrayChunksLength = Array (`threads-pool`+1) {0}
//    val length = numVerticies / `threads-pool`
//    val remainder = numVerticies % `threads-pool`
//    if ( remainder == 0) {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//    } else if (numVerticies < `threads-pool`) {
//        arrayChunksLength[1] = numVerticies
//    } else {
//        for (i in 1..`threads-pool`) {
//            arrayChunksLength[i] = length
//        }
//        arrayChunksLength[1] += remainder
//    }
//    return arrayChunksLength
//}
//
//fun calculateChunksLeftBorders(chunksLength: Array<Int>): Array<Int> {
//    var memLeftBorder = Array(`threads-pool`) {0}
//    for (i in 1 until `threads-pool`) {
//        for (j in 1..i) {
//            memLeftBorder[i] += chunksLength[j]
//        }
//    }
//    return memLeftBorder
//}
//
////fun main() {
////    val executor = Executors.newFixedThreadPool(5)
////    for (i in 0..9) {
////        val worker = Runnable { println("Hello this is thread " + i) }
////        executor.execute(worker)
////    }
////    executor.shutdown()
////    while (!executor.isTerminated) {
////    }
////    println("Finished all threads")
////}
//
//fun Floyd(graph: Graph): Array<Array<Double>> {
//    val vertices = graph.vertices
//    val edges = graph.edges
//    val numVertices = vertices.size
//    val distances = Array(numVertices) { Array(numVertices) {Double.POSITIVE_INFINITY} }
//    for ( i in 0 until numVertices) {
//        for (j in 0 until numVertices) {
//            if (i == j) {
//                distances[i][j] = 0.0
//            } else {
//                checkInEdges(i, j, edges, distances)
//            }
//        }
//    }
//
//    val threadPool = Executors.newFixedThreadPool(`threads-pool`)
//    val chunksLength = calculateChunksLength(numVertices)
//    val leftBorders = calculateChunksLeftBorders(chunksLength)
//
//
//    for (t in 1..`threads-pool`) {
//        val task = Runnable {
//            println("${t-1} = ${leftBorders[t-1]} ${t} = ${chunksLength[t]}")
//            for (k in 0 + leftBorders[t-1] until leftBorders[t-1] + chunksLength[t]) {
//                for (i in 0 until `threads-pool`) {
//                    for (j in 0 until `threads-pool`) {
//                        distances[i][j] = min(distances[i][j], distances[i][k] + distances[k][j])
//                    }
//                }
//            }
//        }
//        threadPool.execute(task)
//    }
//    threadPool.shutdown()
//    return distances
//}

//object FloydWarshall {
//    private const val I = Int.MAX_VALUE // Infinity
//    const val dim = 5000 // Size of Matrix
//    private const val fill = 0.3 // fill variable for matrix generation
//    private const val maxDistance = 100 //distance variable for matrix generation
//    private val adjacencyMatrix = Array(dim) { IntArray( dim)}
//    private val d = Array(dim) { IntArray(dim) }
//    private var numberThreads = 1 // Number of threads here
//    private val secondMatrix = Array(dim) { IntArray(dim)} // Second Copy of Matrix for Multi-Threaded version
//
//    /*
//     * Generate a randomized matrix to use for the algorithm.
//     */
//    private fun generateMatrix() {
//        val random = Random()
//        for (i in 0 until dim) {
//            for (j in 0 until dim) {
//                if (i != j) adjacencyMatrix[i][j] = I
//            }
//        }
//        var i = 0
//        while (i < dim * dim * fill) {
//            adjacencyMatrix[random.nextInt(dim)][random.nextInt(dim)] = random.nextInt(maxDistance + 1)
//            i++
//        }
//    }
//
//    /*
//     * Execute Floyd Warshall on adjacencyMatrix.
//     */
//    private fun execute() {
//        for (i in 0 until dim) {
//            for (j in 0 until dim) {
//                d[i][j] = adjacencyMatrix[i][j]
//                if (i == j) {
//                    d[i][j] = 0
//                }
//            }
//        }
//        for (k in 0 until dim) {
//            for (i in 0 until dim) {
//                for (j in 0 until dim) {
//                    if (d[i][k] == I || d[k][j] == I) {
//                        continue
//                    } else if (d[i][j] > d[i][k] + d[k][j]) {
//                        d[i][j] = d[i][k] + d[k][j]
//                    }
//                }
//            }
//        }
//    }
//
//    /*
//     * Execute Floyd Warshall on adjacencyMatrix using thread pools.
//     */
//    @Throws(InterruptedException::class)
//    private fun executeMultiThread() {
//        val threadPool = Executors.newFixedThreadPool(numberThreads) //Creates a Thread Pool of size Number of Threads
//
//        for (k in 0 until dim) {
//            for (i in 0 until dim) {
//                val nextTask = ParallelTask(secondMatrix, k, i)
//                threadPool.execute(nextTask) // Takes a thread from the pool and executes the runnable parallel Task
//            }
//        }
//        threadPool.shutdown() //shutdown thread pool
//
//        while (!threadPool.awaitTermination(Int.MAX_VALUE.toLong(), TimeUnit.SECONDS)) { }
//    }
//
//    /*
//     * Compare two matrices, matrix1[dim][dim] and matrix2[dim][dim] and
//     * print whether they are equivalent.
//     */
//    private fun compare(matrix1: Array<IntArray>, matrix2: Array<IntArray>) {
//        for (i in 0 until dim) {
//            for (j in 0 until dim) {
//                if (matrix1[i][j] != matrix2[i][j]) {
//                    println("Comparison failed")
//                }
//            }
//        }
//        println("Comparison succeeded")
//    }
//
//    /**
//     * This is my main method. I Kept the original code and added my multi thread code below,
//     * this way on run time it will print out both sequential and parallel times
//     * @param args
//     * @throws InterruptedException
//     */
//    @Throws(InterruptedException::class)
//    @JvmStatic
//    fun main(args: Array<String>) {
//        var start: Long
//        var end: Long // start and end variables for the timers
//        generateMatrix() // generates the matrix
//        //start = System.nanoTime(); // starts timer
//        //execute(); // calls execute method for single threaded version
//        //end = System.nanoTime(); // ends timer
//        //System.out.println("********* Sequential Time Floyd Warshall ********");
//        //System.out.println(numberThreads + " Thread  time consumed: " + (double)(end - start) / 1000000000); // print message
//        //System.out.println(); //prints blank line
//        /**
//         * This will loop 10 times each time it increase the number of threads
//         * This is so i can test 1 - 10 threads in one run.
//         */
//        println("********* Parallel Time Floyd Warshall **********")
//        for (i in 0..8) {
//            numberThreads += 1 // increments the number of threads
//            start = System.nanoTime() // starts timer
//            executeMultiThread() // calls execute for multi thread method
//            end = System.nanoTime() // ends timer
//            println(numberThreads.toString() + " Threads time consumed: " + (end - start).toDouble() / 1000000000) // print message
//            compare(d, secondMatrix) // compares to make sure there equal
//            println() // prints blank line
//        }
//    }
//}
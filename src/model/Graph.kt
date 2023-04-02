package model

class Graph() {
    internal val verticesMap = hashMapOf<VertexId, Vertex>()

    internal val edgesMap = hashMapOf<Pair<VertexId, VertexId>, Edge>()

    val verticesId get() = verticesMap.keys.toList()

    val vertices get() = verticesMap.values.toList()

    val edges get() = edgesMap.values.toList()

    fun getWeight(start: VertexId, end: VertexId) = edgesMap[start to end]?.weight ?: 0

    fun addVertex(
        id: VertexId
    ) {
        verticesMap.getOrPut(id) {
            Vertex(id)
        }
    }

    fun addEdge(start: VertexId, end: VertexId, weight: Double = 1.0) {
        edgesMap.getOrPut(start to end) {
            Edge(
                verticesMap.getValue(start),
                verticesMap.getValue(end),
                weight
            )
        }
    }
}
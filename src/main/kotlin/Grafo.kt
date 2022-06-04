import java.util.function.Consumer

class Grafo() {
    private val vertices: MutableList<Vertice> = ArrayList()
    private val distanciaVertices: MutableMap<Vertice, Int> = HashMap()
    private val rota: MutableMap<Vertice, Vertice?> = HashMap()
    fun printShortestRoutes(start: Vertice) {

        vertices.forEach(Consumer { c: Vertice ->
            distanciaVertices[c] = INFINITE
            rota[c] = null
        })

        distanciaVertices[start] = 0
        val j: MutableList<Vertice> = ArrayList()
        val i: MutableList<Vertice> = ArrayList(vertices)
        while (!i.isEmpty()) {
            val minimalDistance = extractMin(i)
            i.remove(minimalDistance)
            j.add(minimalDistance)
            for (v: Aresta in minimalDistance.getVerticeConnectionList()) {
                if (distanciaVertices[v.to]!! > distanciaVertices[minimalDistance]!! + v.distance) {
                    distanciaVertices[v.to] = distanciaVertices.get(minimalDistance)!! + v.distance
                    rota[v.to] = minimalDistance
                }
            }
        }
        printShortestPath(start)
    }

    fun addVertice(vertice: Vertice) {
        vertices.add(vertice)
    }

    private fun printShortestPath(source: Vertice) {
        for (c: Vertice in vertices) {
            var caminho = c.name
            var p = rota[c]
            while (p != null) {
                caminho = p.name + " -> " + caminho
                p = rota[p]
            }
            println(
                "Distancia de " + source.name + " para " + c.name + ": " + distanciaVertices[c] + " metros"
            )
            println("Caminho mais curto: $caminho\n")
        }
    }

    private fun extractMin(Q: List<Vertice>): Vertice {
        var min = Q[0]
        for (c: Vertice in Q) {
            if (distanciaVertices[c]!! < (distanciaVertices[min])!!) {
                min = c
            }
        }
        return min
    }

    companion object {
        private val INFINITE = Int.MAX_VALUE
    }
}
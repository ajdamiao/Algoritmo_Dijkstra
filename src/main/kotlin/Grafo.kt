import java.util.function.Consumer

class Grafo() {
    private val vertices: MutableList<Vertice> = ArrayList()
    private val aresta: MutableMap<Vertice, Int> = HashMap()
    private val rota: MutableMap<Vertice, Vertice?> = HashMap()
    fun printShortestRoutes(start: Vertice, end: Vertice) {

        vertices.forEach(Consumer { c: Vertice ->
            aresta[c] = INFINITE
            rota[c] = null
        })

        val j: MutableList<Vertice> = ArrayList()
        val i: MutableList<Vertice> = ArrayList(vertices)
        aresta[start] = 0

        while (i.isNotEmpty()) {
            val minimalDistance = extractMin(i)
            i.remove(minimalDistance)
            j.add(minimalDistance)
            for (v: Aresta in minimalDistance.getVerticeConnectionList()) {
                if (aresta[v.to]!! > aresta[minimalDistance]!! + v.distance) {
                    aresta[v.to] = aresta[minimalDistance]!! + v.distance
                    rota[v.to] = minimalDistance
                }
            }
        }
        printShortestPath(start, end)
    }

    fun addVertice(vertice: Vertice) {
        vertices.add(vertice)
    }

    private fun printShortestPath(source: Vertice, end: Vertice) {
        for (c: Vertice in vertices) {
            if(c.name == end.name) {

                var caminho = c.name
                var p = rota[c]
                while (p != null) {
                    caminho = p.name + " -> " + caminho
                    p = rota[p]
                }
                println(
                    "Distancia de " + source.name + " para " + c.name + ": " + aresta[c] + " metros"
                )
                println("Caminho mais curto: $caminho\n")
            }
        }
    }

    private fun extractMin(Q: List<Vertice>): Vertice {
        var min = Q[0]
        for (c: Vertice in Q) {
            if (aresta[c]!! < (aresta[min])!!) {
                min = c
            }
        }
        return min
    }

    companion object {
        private val INFINITE = Int.MAX_VALUE
    }
}
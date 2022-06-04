class Vertice(val name: String) {
    private val arestaConnections: MutableList<Aresta> = ArrayList()
    fun getVerticeConnection(to: Aresta) {
        arestaConnections.add(to)
    }

    fun getVerticeConnectionList(): List<Aresta> {
        return arestaConnections
    }
}
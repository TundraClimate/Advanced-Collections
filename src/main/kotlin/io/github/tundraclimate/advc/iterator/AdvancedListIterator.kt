package io.github.tundraclimate.advc.iterator

class AdvancedListIterator<T> internal constructor(private val t: MutableList<T>, i: Int = 0) :
    AdvancedIterator<T>, MutableListIterator<T> {
    private var index = i
    override fun add(element: T) {
        t.add(index, element)
    }

    override fun hasNext(): Boolean = t.size > index
    override fun hasPrevious(): Boolean = index > 0

    override fun next(): T {
        val tm = t[index]
        index++
        return tm
    }

    override fun nextIndex(): Int = index + 1

    override fun previous(): T = t[index - 1]

    override fun previousIndex(): Int = index - 1

    override fun remove() {
        t.removeAt(index)
    }

    override fun set(element: T) {
        t[index] = element
    }
}
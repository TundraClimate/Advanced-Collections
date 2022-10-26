package io.github.tundraclimate.advc

import io.github.tundraclimate.advc.iterator.AdvancedListIterator

class RestorableList<T : Any>(historySize: Int) : Iterable<T>, AdvancedList<T>, MutableList<T> {
    private val list = mutableListOf<T>()
    private val hist = LimitedStash<T>(historySize)
    override val size: Int
        get() = list.size

    fun undoHistory() {
        list.add(hist.removeLast())
    }

    override fun clear() = list.clear()

    override fun addAll(elements: Collection<T>): Boolean = list.addAll(elements)

    override fun addAll(index: Int, elements: Collection<T>): Boolean = list.addAll(index, elements)

    override fun add(index: Int, element: T) = list.add(index, element)

    override fun add(element: T): Boolean = list.add(element)

    override fun isEmpty(): Boolean = size == 0

    override fun containsAll(elements: Collection<T>): Boolean = list.containsAll(elements)

    override fun contains(element: T): Boolean = list.contains(element)

    override fun iterator(): MutableIterator<T> = AdvancedListIterator(list)

    override fun get(index: Int): T = list[index]

    override fun listIterator(): MutableListIterator<T> = AdvancedListIterator(list)

    override fun listIterator(index: Int): MutableListIterator<T> = AdvancedListIterator(list, index)

    override fun removeAt(index: Int): T {
        hist.add(list[index])
        return list.removeAt(index)
    }

    override fun set(index: Int, element: T): T = list.set(index, element)

    override fun retainAll(elements: Collection<T>): Boolean = list.retainAll(elements)

    override fun removeAll(elements: Collection<T>): Boolean {
        for (e in elements) {
            hist.add(list[list.indexOf(e)])
        }
        return list.removeAll(elements)
    }

    override fun remove(element: T): Boolean {
        hist.add(list[list.indexOf(element)])
        return list.remove(element)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> = list.subList(fromIndex, toIndex)

    override fun lastIndexOf(element: T): Int = list.lastIndexOf(element)

    override fun indexOf(element: T): Int = list.indexOf(element)
}
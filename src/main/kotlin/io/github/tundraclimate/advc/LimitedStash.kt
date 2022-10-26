package io.github.tundraclimate.advc

import io.github.tundraclimate.advc.iterator.AdvancedListIterator

class LimitedStash<T>(private val max: Int) : AdvancedList<T>, MutableList<T> {
    private val stash = mutableListOf<T>()
    override val size: Int
        get() = stash.size

    override fun clear() = stash.clear()

    override fun addAll(elements: Collection<T>): Boolean {
        val elms = elements.toMutableList()
        val newElms = (0 until max).mapNotNull { elms.removeLastOrNull() }
        repeat(newElms.size) { stash.removeFirst() }
        return stash.addAll(newElms)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean = addAll(elements)

    override fun add(index: Int, element: T) {
        if (size + 1 > max)
            stash.removeFirst()
        stash.add(index, element)
    }

    override fun add(element: T): Boolean {
        if (size + 1 > max)
            stash.removeFirst()
        return stash.add(element)
    }

    override fun get(index: Int): T = stash.get(index)

    override fun isEmpty(): Boolean = stash.isEmpty()

    override fun iterator(): MutableIterator<T> = AdvancedListIterator(stash)

    override fun listIterator(): MutableListIterator<T> = AdvancedListIterator(stash)

    override fun listIterator(index: Int): MutableListIterator<T> = AdvancedListIterator(stash)

    override fun removeAll(elements: Collection<T>): Boolean = stash.removeAll(elements)

    override fun removeAt(index: Int): T = stash.removeAt(index)

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> = stash.subList(fromIndex, toIndex)

    override fun set(index: Int, element: T): T = stash.set(index, element)

    override fun retainAll(elements: Collection<T>): Boolean = stash.retainAll(elements)

    override fun remove(element: T): Boolean = stash.remove(element)

    override fun lastIndexOf(element: T): Int = stash.lastIndexOf(element)

    override fun indexOf(element: T): Int = stash.indexOf(element)

    override fun containsAll(elements: Collection<T>): Boolean = stash.containsAll(elements)

    override fun contains(element: T): Boolean = stash.contains(element)
}
package com.example.recursivegrid.logic

import androidx.compose.runtime.mutableStateListOf

class GridInteractor {

    private val _gridList = mutableStateListOf<GridCell>()
    val gridState: List<GridCell> get() = _gridList

    init {
        repeat(9) {
            _gridList.add(GridCell(0, false))
        }
    }

    fun onBoxClicked(index: Int) {
        if (index !in _gridList.indices) return
        if (_gridList[index].isLocked) return

        processUpdates(index, 1)
    }

    private fun processUpdates(startIndex: Int, startDelta: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(startIndex to startDelta)

        while (queue.isNotEmpty()) {
            val (currentIndex, delta) = queue.removeFirst()

            if (currentIndex !in _gridList.indices) continue
            if (_gridList[currentIndex].isLocked) continue

            val currentCell = _gridList[currentIndex]
            val newValue = currentCell.value + delta
            val isNowLocked = newValue >= 15

          
            _gridList[currentIndex] = currentCell.copy(
                value = newValue,
                isLocked = isNowLocked
            )

            if (isNowLocked) continue

            // Calculate and enqueue ripples
            enqueueRipples(currentIndex, newValue, queue)
        }
    }

    private fun enqueueRipples(index: Int, value: Int, queue: ArrayDeque<Pair<Int, Int>>) {
        if (value % 3 == 0) {
            getRightNeighbor(index)?.let { rightIndex ->
                queue.add(rightIndex to -1)
            }
        }

        if (value % 5 == 0) {
            getBottomNeighbor(index)?.let { bottomIndex ->
                queue.add(bottomIndex to 2)
            }
        }
    }

    private fun getRightNeighbor(index: Int): Int? {
        return if (index % 3 != 2) index + 1 else null
    }

    private fun getBottomNeighbor(index: Int): Int? {
        return if (index < 6) index + 3 else null
    }

    fun resetGrid() {
        for (i in _gridList.indices) {
            _gridList[i] = GridCell(0, false)
        }
    }
}

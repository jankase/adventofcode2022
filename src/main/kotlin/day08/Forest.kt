package day08

data class Forest(val trees: List<Tree>) {
    companion object {
        fun valueOf(value: List<String>) =
            Forest(
                value.mapIndexed { rowIndex, rowTreesHeight ->
                    rowTreesHeight.mapIndexed { columnIndex, heightChar ->
                        Tree(rowIndex, columnIndex, heightChar.toString().toInt())
                    }
                }.flatten()
            )
    }

    fun numberOfVisibleTrees(): Int {
        resetVisibleTrees()
        markVisibleTrees()
        return trees.count { it.isVisible }
    }

    fun highestScenicScore(): Int {
        resetScenicScore()
        calculateScenicScore()
        return trees.maxOf { it.scenicScore }
    }

    private fun resetScenicScore() = trees.forEach { it.scenicScore = 0 }

    private fun lookScore(tree: Tree, comparator: Comparator<in Tree>, function: (Tree, Tree) -> Boolean): Int =
        trees
            .filter { function(it, tree) }
            .toMutableList()
            .sortedWith(comparator)
            .takeVisibleTrees(tree.height)
            .size

    private fun calculateScenicScore() {
        trees.forEach { tree ->
            val lookLeftScore = lookScore(tree, compareByDescending { it.column }, ::lookLeftCondition)
            val lookRightScore = lookScore(tree, compareBy { it.column }, ::lookRightCondition)
            val lookUpScore = lookScore(tree, compareByDescending { it.row }, ::lookUpCondition)
            val lookDownScore = lookScore(tree, compareBy { it.row }, ::lookDownCondition)
            tree.scenicScore = lookLeftScore * lookRightScore * lookUpScore * lookDownScore
        }
    }

    private fun resetVisibleTrees() = trees.forEach { it.isVisible = false }

    private fun markVisibleTrees() =
        trees.forEach { tree ->
            tree.isVisible = isTreeVisible(tree) { lookLeftCondition(it, tree) } ||
                isTreeVisible(tree) { lookRightCondition(it, tree) } ||
                isTreeVisible(tree) { lookUpCondition(it, tree) } ||
                isTreeVisible(tree) { lookDownCondition(it, tree) }
        }

    private fun isTreeVisible(tree: Tree, lineOfViewPickerCondition: (Tree) -> Boolean): Boolean =
        trees.count { lineOfViewPickerCondition(it) && it.height >= tree.height } == 1

    private fun lookLeftCondition(tree: Tree, treeFromForest: Tree): Boolean =
        tree.row == treeFromForest.row && tree.column <= treeFromForest.column

    private fun lookRightCondition(tree: Tree, treeFromForest: Tree): Boolean =
        tree.row == treeFromForest.row && tree.column >= treeFromForest.column

    private fun lookUpCondition(tree: Tree, treeFromForest: Tree): Boolean =
        tree.column == treeFromForest.column && tree.row <= treeFromForest.row

    private fun lookDownCondition(tree: Tree, treeFromForest: Tree): Boolean =
        tree.column == treeFromForest.column && tree.row >= treeFromForest.row
}

private fun Iterable<Tree>.takeVisibleTrees(currentHeight: Int): List<Tree> {
    val result = mutableListOf<Tree>()
    forEachIndexed { index, t ->
        if (index == 0) return@forEachIndexed
        result.add(t)
        if (t.height >= currentHeight) return result
    }
    return result
}

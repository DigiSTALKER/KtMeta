package io.github.hochikong.ktmeta.device

import io.github.hochikong.ktmeta.predefined.FileType

/**
 * Data class for building files tree.
 * @param selfPath Absolute path of this file or directory.
 * @param rootPath Absolute path, mark as root path. If [selfPath] == [rootPath], [hasFather] will be false.
 * @param hasFather Root path has no father in FileRow, but it may be a child of a directory in filesystem.
 * @param fatherPath Father path of [selfPath], but when hasFather is false, it will be ".".
 * @param hasChild If [selfPath] is a directory with child dirs or files, it will be true. If [selfPath] is file,
 * will be false.
 * @param type If [selfPath] is a directory, it will be FileType.Directory or FileType.File in Support.kt.
 * */
data class FileRow(
    val selfPath: String,
    val rootPath: String,
    val hasFather: Boolean,
    val fatherPath: String,
    val hasChild: Boolean,
    val type: FileType
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileRow

        if (selfPath != other.selfPath) return false
        if (rootPath != other.rootPath) return false
        if (hasFather != other.hasFather) return false
        if (fatherPath != other.fatherPath) return false
        if (hasChild != other.hasChild) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selfPath.hashCode()
        result = 31 * result + rootPath.hashCode()
        result = 31 * result + hasFather.hashCode()
        result = 31 * result + fatherPath.hashCode()
        result = 31 * result + hasChild.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String {
        return "FileRow(selfPath='$selfPath', rootPath='$rootPath', hasFather=$hasFather, " +
                "fatherPath='$fatherPath', hasChild=$hasChild, type=$type)"
    }


}

/**
 * A factory method to return FileRow
 * */
fun fileRowBuilder(
    selfPath: String,
    rootPath: String,
    hasFather: Boolean,
    fatherPath: String,
    hasChild: Boolean,
    type: FileType
): FileRow {
    var hasFather0 = hasFather
    var fatherPath0: String = fatherPath
    if (selfPath == rootPath) {
        hasFather0 = false
        fatherPath0 = "."
    }
    if (!hasFather) {
        fatherPath0 = "."
    }
    return FileRow(selfPath, rootPath, hasFather0, fatherPath0, hasChild, type)
}
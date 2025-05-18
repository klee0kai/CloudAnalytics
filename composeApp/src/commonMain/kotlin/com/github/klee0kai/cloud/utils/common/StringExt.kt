package com.github.klee0kai.cloud.utils.common

fun Iterable<String>.findCommonPgk(): String {
    var commonPkg = firstOrNull() ?: return ""
    forEach { pkg ->
        while (!pkg.startsWith(commonPkg)) {
            commonPkg = commonPkg.dropLast(1)
        }
    }

    if (commonPkg.endsWith(".")) {
        commonPkg = commonPkg.dropLast(1)
    }
    return commonPkg
}

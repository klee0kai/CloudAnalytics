package com.github.klee0kai.cloud.utils.error

import java.io.IOException

class FSNoAccessError(message: String? = null, cause: Throwable? = null) : IOException(message, cause)

class FSNoFileName(message: String? = null, cause: Throwable? = null) : IOException(message, cause)

class FSDuplicateError(message: String? = null, cause: Throwable? = null) : IOException(message, cause)


package com.github.klee0kai.cloud.devkit

//@Composable
//fun Screen(
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit,
//) = OverlayContainer {
////    val router by LocalRouter.currentRef
////
////    val imeVisible by animateTargetFaded(WindowInsets.isIme)
////    BackHandler(enabled = true) {
////        when {
////            imeVisible.current -> {
////                router?.hideKeyboard()
////            }
////
////            else -> {
////                router?.back(BackType.FromAppIfNeed)
////            }
////        }
////    }
//
//    Box(
//        modifier = modifier
////            .pointerInput(Unit) { detectTapGestures { router?.hideKeyboard() } }
//    ) {
//        content()
//    }
//}
//package com.github.klee0kai.cloud.devkit.components.grapth
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.dk.kuiver.model.Kuiver
//import com.dk.kuiver.model.buildKuiver
//import com.dk.kuiver.model.edges
//import com.dk.kuiver.model.layout.LayoutConfig
//import com.dk.kuiver.model.layout.LayoutDirection
//import com.dk.kuiver.model.nodes
//import com.dk.kuiver.rememberKuiverViewerState
//import com.dk.kuiver.renderer.KuiverViewer
//import com.dk.kuiver.renderer.KuiverViewerConfig
//import com.dk.kuiver.ui.EdgeContent
//
//
//@Composable
//fun MyGraphViewer(
//    kuiver: Kuiver = kuiverDummy(),
//) {
//    val layoutConfig = LayoutConfig.Hierarchical(
//        direction = LayoutDirection.HORIZONTAL
//    )
//
//    val viewerState = rememberKuiverViewerState(
//        initialKuiver = kuiver,
//        layoutConfig = layoutConfig
//    )
//
//    KuiverViewer(
//        state = viewerState,
//        config = KuiverViewerConfig(),
//        nodeContent = { node ->
//            // Customize node appearance
//            Box(
//                modifier = Modifier
//                    .size(80.dp)
//                    .background(Color.Blue, CircleShape),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(node.id, color = Color.White)
//            }
//        },
//        edgeContent = { edge, from, to ->
//            EdgeContent(from, to, color = Color.Gray)
//        }
//    )
//}
//
//
//fun kuiverDummy(
//) = buildKuiver {
//    nodes("A", "B", "C")
//
//    edges(
//        "A" to "B",
//        "B" to "C",
//        "A" to "C"
//    )
//}
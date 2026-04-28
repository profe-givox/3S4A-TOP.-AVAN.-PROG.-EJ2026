package net.ivanvega.miinventorykmpcomposedesktop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.ivanvega.miinventorykmpcomposedesktop.cache.AndroidDatabaseDriverFactory
import net.ivanvega.miinventorykmpcomposedesktop.cache.InventoryExporter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(AndroidDatabaseDriverFactory(this),
                InventoryExporter(this))
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}
package com.lfmteixeira.composefinances.ui.transactions.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lfmteixeira.composefinances.ui.chart.pie.PieChart
import com.lfmteixeira.composefinances.ui.chart.pie.PieChartData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TransactionDetail(
    viewModel: TransactionDetailViewModel,
    navigateBack: () -> Unit
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = "${state.value.description}")},
                navigationIcon = {
                    IconButton(onClick = {navigateBack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            ConstraintLayout(
                Modifier
                    .fillMaxSize()
                    .padding(padding)) {
                val (description, amount, category) = createRefs()
                Text(
                    text = state.value.description,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.constrainAs(description) {
                        start.linkTo(parent.start)
                    }
                )
                Text(
                    text = state.value.amount,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.constrainAs(amount) {
                        end.linkTo(parent.end)
                    }
                )
                Text(
                    text = state.value.category,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.constrainAs(category) {
                        start.linkTo(parent.start)
                        top.linkTo(description.bottom)
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 15.dp)
                ) {
                    PieChart(
                        pieChartData = PieChartData(
                            slices = listOf(
                                PieChartData.Slice(25f, MaterialTheme.colors.primary),
                                PieChartData.Slice(42f, MaterialTheme.colors.secondary),
                                PieChartData.Slice(23f, MaterialTheme.colors.error)
                            )
                        )
                    )
                }
            }
        }
    )
}
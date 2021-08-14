package me.pavi2410.vrcc.models

data class ResultModel(
    val isCompatible: Pair<Int, Int>,
    val detailModel: List<DetailModel>
)
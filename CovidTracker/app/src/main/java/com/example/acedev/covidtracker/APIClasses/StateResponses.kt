package com.example.acedev.covidtracker.APIClasses

data class StateResponses(
    val statewise : List<StateData>
)

data class StateData(
    val active : String,
    val confirmed : String,
    val deaths : String,
    val recovered : String,
    val state : String,
    val lastupdatedtime : String,
    val deltaconfirmed : String,
    val deltadeaths : String,
    val deltarecovered : String
)
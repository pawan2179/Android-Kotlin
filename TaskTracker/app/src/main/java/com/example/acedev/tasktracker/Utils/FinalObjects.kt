package com.example.acedev.tasktracker.Utils

class FinalObjects {

    companion object {
        var hourList = ArrayList<String>()
        var minuteList = ArrayList<String> ()
        var priorityList = ArrayList<Int> ()

        fun getSpinnerHourList() : ArrayList<String> {
            if(hourList.size == 0) {
                for(i in 0..23)    hourList.add(" " + i.toString() + " ")
            }
            return hourList
        }

        fun getSpinnerMinuteList() : ArrayList<String> {
            if(minuteList.size == 0) {
                for(i in 0..59)    minuteList.add(" " + i.toString() + " ")
            }
            return minuteList
        }

        fun getSpinnerPriorityList() : ArrayList<Int> {
            if(priorityList.size == 0) {
                for(i in 1..5)    priorityList.add(i)
            }
            return priorityList
        }
    }
}
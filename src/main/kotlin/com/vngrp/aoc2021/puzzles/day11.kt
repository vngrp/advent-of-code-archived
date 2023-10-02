//package com.vngrp.aoc2021.puzzles
//
//import GDay
//import getAdjacents
//import java.io.File
//import repeat
//import toAsciiInt
//
//var bigBoyCounter = 0
//
//class Day11: GDay<List<DumboOctopus>> {
//    override val examplePuzzle1 = 1656
//    override val examplePuzzle2 = 0
//
//    override fun puzzle1(input: List<DumboOctopus>): Int {
//        100.repeat(input) { acc ->
//            acc
//                .map { it.increaseEnergy() }
//                .map { it.flash() }
//        }
//
//        return bigBoyCounter
//    }
//
//    override fun puzzle2(input: List<DumboOctopus>): Int {
//        return input
//                .count()
//    }
//
//    override fun parseInput(file: File): List<DumboOctopus> {
//        val width = file.useLines { it.first() }.length
//        val octos = file
//            .readLines()
//            .flatMapIndexed { y, line ->
//                line.mapIndexed { x, energy ->
//                    DumboOctopus(energy.toAsciiInt(), Point(x, y))
//                }
//            }
//
//        return octos.map {
//            it.copy(neighbours = octos.getAdjacents(it.location, width = width, includeDiagonal = true))
//        }
//    }
//}
//
//
//data class DumboOctopus(val energy: Int, val location: Point, val neighbours: List<DumboOctopus> = emptyList()) {
//    fun increaseEnergy(): DumboOctopus = copy(energy = energy + 1)
//    fun flash(): DumboOctopus {
//        return if (energy >= 9) {
//            bigBoyCounter++
//            copy(energy = 0, neighbours = neighbours.map { it.increaseEnergy().flash() })
//        } else {
//            this
//        }
//    }
//}
//

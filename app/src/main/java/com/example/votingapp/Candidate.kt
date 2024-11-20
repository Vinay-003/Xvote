// Candidate.kt
package com.example.votingapp

data class Candidate(val name: String, val candidateAddress: String)

object CandidateData {
    val sampleCandidates = listOf(
        Candidate("Candidate 1" , " hg"),
        Candidate("Candidate 2", "hj"),
        Candidate("Candidate 3" , "gvfjg"),
        Candidate("Candidate 4" , "bj")
    )
}
package com.example.votingapp

data class Candidate(
    val name: String,
    val candidateAddress: String,
    val description: String, // Description of the candidate
    val party: String, // Candidate's party
    val imageResId: Int // Resource ID for the candidate's image
)

object CandidateData {
    val sampleCandidates = listOf(
        Candidate("Candidate 1", "hg", "Description for Candidate 1", "Party A", R.drawable.img_1), // Replace with actual drawable IDs
        Candidate("Candidate 2", "hj", "Description for Candidate 2", "Party B", R.drawable.img_2),
        Candidate("Candidate 3", "gvfjg", "Description for Candidate 3", "Party C", R.drawable.img_3),
        Candidate("Candidate 4", "bj", "Description for Candidate 4", "Party D", R.drawable.img)
    )
}
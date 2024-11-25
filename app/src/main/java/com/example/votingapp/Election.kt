package com.example.votingapp

data class Election(val name: String, val candidates: List<Candidate>)

object ElectionData {
    val sampleElections = listOf(
        Election(
            "Election 1",
            listOf(
                Candidate("Candidate 1", "df", "Description for Candidate 1", "Party A", R.drawable.img_1), // Replace with actual drawable IDs
                Candidate("Candidate 2", "df", "Description for Candidate 2", "Party B", R.drawable.img_2)
            )
        ),
        Election(
            "Election 2",
            listOf(
                Candidate("Candidate 3", "df", "Description for Candidate 3", "Party C", R.drawable.img_3),
                Candidate("Candidate 4", "df", "Description for Candidate 4", "Party D", R.drawable.img)
            )
        )
    )
}
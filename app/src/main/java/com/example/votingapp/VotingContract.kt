//// VotingContract.kt
package com.example.votingapp

//import org.web3j.abi.FunctionEncoder
//import org.web3j.abi.TypeReference
//import org.web3j.abi.datatypes.Function
//import org.web3j.abi.datatypes.Utf8String
//import org.web3j.abi.datatypes.generated.Uint256
//import org.web3j.abi.datatypes.Address
//import org.web3j.crypto.Credentials
//import org.web3j.protocol.core.methods.response.TransactionReceipt
//import org.web3j.tx.RawTransactionManager
//import org.web3j.tx.gas.DefaultGasProvider
//import java.math.BigInteger
//
//class VotingContract(private val contractAddress: String, private val credentials: Credentials) {
//    private val web3j = Web3jManager.web3j
//    private val transactionManager = RawTransactionManager(web3j, credentials)
//    private val gasProvider = DefaultGasProvider()
//
//    fun createElection(eventName: String): TransactionReceipt {
//        val function = Function(
//            "createElection",
//            listOf(Utf8String(eventName)),
//            emptyList()
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val transaction = transactionManager.sendTransaction(
//            gasProvider.gasPrice,
//            gasProvider.gasLimit,
//            contractAddress,
//            encodedFunction,
//            BigInteger.ZERO
//        )
//        return transaction.send()
//    }
//
//    fun registerCandidates(electionId: BigInteger, name: String, age: BigInteger, candidateAddress: String): TransactionReceipt {
//        val function = Function(
//            "registerCandidates",
//            listOf(Uint256(electionId), Utf8String(name), Uint256(age), Address(candidateAddress)),
//            emptyList()
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val transaction = transactionManager.sendTransaction(
//            gasProvider.gasPrice,
//            gasProvider.gasLimit,
//            contractAddress,
//            encodedFunction,
//            BigInteger.ZERO
//        )
//        return transaction.send()
//    }
//
//    fun verifyVoter(electionId: BigInteger, voterAddress: String): TransactionReceipt {
//        val function = Function(
//            "verifyVoter",
//            listOf(Uint256(electionId), Address(voterAddress)),
//            emptyList()
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val transaction = transactionManager.sendTransaction(
//            gasProvider.gasPrice,
//            gasProvider.gasLimit,
//            contractAddress,
//            encodedFunction,
//            BigInteger.ZERO
//        )
//        return transaction.send()
//    }
//
//    fun setVote(electionId: BigInteger, candidateAddress: String): TransactionReceipt {
//        val function = Function(
//            "setVote",
//            listOf(Uint256(electionId), Address(candidateAddress)),
//            emptyList()
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val transaction = transactionManager.sendTransaction(
//            gasProvider.gasPrice,
//            gasProvider.gasLimit,
//            contractAddress,
//            encodedFunction,
//            BigInteger.ZERO
//        )
//        return transaction.send()
//    }
//
//    fun setVotingState(electionId: BigInteger, isActive: Boolean): TransactionReceipt {
//        val function = Function(
//            "setVotingState",
//            listOf(Uint256(electionId), org.web3j.abi.datatypes.Bool(isActive)),
//            emptyList()
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val transaction = transactionManager.sendTransaction(
//            gasProvider.gasPrice,
//            gasProvider.gasLimit,
//            contractAddress,
//            encodedFunction,
//            BigInteger.ZERO
//        )
//        return transaction.send()
//    }
//
//    fun votingStatus(electionId: BigInteger): Boolean {
//        val function = Function(
//            "votingStatus",
//            listOf(Uint256(electionId)),
//            listOf(object : TypeReference<org.web3j.abi.datatypes.Bool>() {})
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val response = web3j.ethCall(
//            org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
//                credentials.address, contractAddress, encodedFunction
//            ), org.web3j.protocol.core.DefaultBlockParameterName.LATEST
//        ).send()
//        return org.web3j.abi.FunctionReturnDecoder.decode(
//            response.value, function.outputParameters
//        )[0].value as Boolean
//    }
//
//    fun getAllCandidates(electionId: BigInteger): List<Candidate> {
//        val function = Function(
//            "getAllCandidates",
//            listOf(Uint256(electionId)),
//            listOf(object : TypeReference<org.web3j.abi.datatypes.DynamicArray<Address>>() {})
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val response = web3j.ethCall(
//            org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
//                credentials.address, contractAddress, encodedFunction
//            ), org.web3j.protocol.core.DefaultBlockParameterName.LATEST
//        ).send()
//        val decoded = org.web3j.abi.FunctionReturnDecoder.decode(
//            response.value, function.outputParameters
//        )
//        return decoded[0].value as List<Candidate>
//    }
//
//    fun getWinner(electionId: BigInteger): Candidate {
//        val function = Function(
//            "getWinner",
//            listOf(Uint256(electionId)),
//            listOf(object : TypeReference<Address>() {})
//        )
//        val encodedFunction = FunctionEncoder.encode(function)
//        val response = web3j.ethCall(
//            org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
//                credentials.address, contractAddress, encodedFunction
//            ), org.web3j.protocol.core.DefaultBlockParameterName.LATEST
//        ).send()
//        val winnerAddress = org.web3j.abi.FunctionReturnDecoder.decode(
//            response.value, function.outputParameters
//        )[0].value as String
//        return getAllCandidates(electionId).first { it.candidateAddress == winnerAddress }
//    }
//}
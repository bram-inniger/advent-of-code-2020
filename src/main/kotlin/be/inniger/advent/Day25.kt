package be.inniger.advent

class Day25 {

    private companion object {
        private const val SUBJECT = 7
        private const val DIVISOR = 20_201_227
    }

    fun solveFirst(cardPublicKey: Int, doorPublicKey: Int): Int {
        val cardLoopSize = crackLoopsSize(cardPublicKey)
        val doorLoopSize = crackLoopsSize(doorPublicKey)

        val cardEncryptionKey = crackEncryptionKey(cardPublicKey, doorLoopSize)
        val doorEncryptionKey = crackEncryptionKey(doorPublicKey, cardLoopSize)

        return listOf(cardEncryptionKey, doorEncryptionKey).distinct().single()
    }

    private tailrec fun crackLoopsSize(publicKey: Int, calculatedKey: Long = 1, loopSize: Int = 0): Int =
        if (calculatedKey.toInt() == publicKey) loopSize
        else crackLoopsSize(
            publicKey,
            (calculatedKey * SUBJECT) % DIVISOR,
            loopSize + 1
        )

    private tailrec fun crackEncryptionKey(subject: Int, loopSize: Int, calculatedKey: Long = 1): Int =
        if (loopSize == 0) calculatedKey.toInt()
        else crackEncryptionKey(
            subject,
            loopSize - 1,
            (calculatedKey * subject) % DIVISOR
        )
}

package be.inniger.advent

object Day25 {

    private const val SUBJECT = 7
    private const val DIVISOR = 20_201_227

    fun solveFirst(cardPublicKey: Long, doorPublicKey: Long) =
        listOf(
            crack(cardPublicKey, doorPublicKey),
            crack(doorPublicKey, cardPublicKey)
        ).distinct().single()

    private tailrec fun crack(pubKey1: Long, pubKey2: Long, crackedPubKey: Long = 1, crackedPrivKey: Long = 1): Long =
        if (crackedPubKey == pubKey1) crackedPrivKey
        else crack(
            pubKey1,
            pubKey2,
            (crackedPubKey * SUBJECT) % DIVISOR,
            (crackedPrivKey * pubKey2) % DIVISOR
        )
}

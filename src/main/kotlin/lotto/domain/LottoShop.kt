package lotto.domain

import lotto.domain.entity.common.LottoNumber
import lotto.domain.entity.user.Lotto
import lotto.domain.generator.LottoGenerator

object LottoShop {

    private const val LOTTO_MIN_NUMBER = 1
    private const val LOTTO_MAX_NUMBER = 45
    private const val LOTTO_LENGTH = 6

    fun createLottoTicket(ticketingCount: Int, manualLotto: List<String>): List<Lotto> {

        val lottoList = mutableListOf<Lotto>()

        repeat(ticketingCount) {
            lottoList.add(
                Lotto(
                    lottoNumber = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER)
                        .map { LottoNumber(it) }
                        .shuffled()
                        .take(LOTTO_LENGTH)
                        .sortedBy { it.number }
                )
            )
        }

        for (manualLottoNumber in manualLotto) {
            lottoList.add(
                Lotto(
                    LottoGenerator.generatorLotto(manualLottoNumber)
                )
            )
        }

        return lottoList.toList()
    }
}
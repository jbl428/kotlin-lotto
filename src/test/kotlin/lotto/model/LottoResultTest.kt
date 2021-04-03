package lotto.model

import lotto.model.winning.WinningPlace.FIRST
import lotto.model.winning.WinningPlace.SECOND
import lotto.model.winning.WinningPlace.THIRD
import lotto.model.winning.WinningPlace.FOURTH
import lotto.model.winning.WinningPlace.FIFTH
import lotto.model.winning.WinningCounter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoResultTest {
    @ParameterizedTest
    @MethodSource("lottoResultProvider")
    fun `총 구매비용과 등수별 당첨 횟수가 같으면 같은 LottoResult 이다`(result: LottoResult, otherResult: LottoResult, isEqual: Boolean) {
        assertThat(result == otherResult).isEqualTo(isEqual)
    }

    @ParameterizedTest
    @MethodSource("benefitRateProvider")
    fun `주어진 값을 이용해 수익률을 구한다`(result: LottoResult, benefitRate: Double) {
        assertThat(result.benefitRate).isEqualTo(benefitRate)
    }

    companion object {
        @JvmStatic
        fun lottoResultProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 1, THIRD to 1, FOURTH to 0, FIFTH to 0), Money(10_000)),
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 1, THIRD to 1, FOURTH to 0, FIFTH to 0), Money(10_000)),
                        true
                    )
                },
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 1, THIRD to 1, FOURTH to 0, FIFTH to 0), Money(10_000)),
                        LottoResult(WinningCounter(FIRST to 1, SECOND to 1, THIRD to 0, FOURTH to 0, FIFTH to 0), Money(10_000)),
                        false
                    )
                },
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 1, THIRD to 1, FOURTH to 0, FIFTH to 0), Money(10_000)),
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 1, THIRD to 1, FOURTH to 0, FIFTH to 0), Money(23_000)),
                        false
                    )
                }
            )
        }

        @JvmStatic
        fun benefitRateProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 0, THIRD to 0, FOURTH to 0, FIFTH to 1), Money(5_000)),
                        1.0
                    )
                },
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 0, THIRD to 0, FOURTH to 0, FIFTH to 0), Money(5_000)),
                        0.0
                    )
                },
                Arguments {
                    arrayOf(
                        LottoResult(WinningCounter(FIRST to 0, SECOND to 0, THIRD to 0, FOURTH to 0, FIFTH to 1), Money(14_000)),
                        0.35
                    )
                }
            )
        }
    }
}
import kotlin.test.Test
import kotlin.test.assertEquals
import com.elvis_c.sharedcalculaotr.engine.skill.*
import com.elvis_c.sharedcalculaotr.engine.skill.warlock.WarlockSkillCompute

class SkillComputeTest {

    @Test
    fun testSoulCombo() {

        WarlockSkillCompute.registerAll()

        val ctx = SkillContext(
            slv = 5,
            blv = 260,
            str = 0, agi = 0, vit = 0,
            int = 0, dex = 0, luk = 0,
            pow = 0, sta = 0, wis = 0,
            spl = 100, con = 0, crt = 0
        )

        val result = SkillEngine.compute("soul_combo", ctx)
        val variant = result.variants.first()

        assertEquals(46.8, variant.ratePerHit, 0.0001)
        assertEquals(7, variant.hits)
        println("ratePerHit = ${variant.ratePerHit}")
        println("hits = ${variant.hits}")
    }
}
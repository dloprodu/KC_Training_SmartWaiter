package io.keepcoding.smartwaiter.model

import io.keepcoding.smartwaiter.R

object Dishes {
    private val CARNE_FIESTA = "La carne fiesta, o también llamado cochino en adobo, es un plato hecho a base de cerdo muy popular en la gastronomía canaria, sobre todo en época de festejos y romerías, aunque en alguna isla, como Tenerife, es muy habitual ver este delicioso plato en el menú de restaurantes y guachinches."
    private val COSTILLAS_SA = "Otro de nuestros platos típicos, no hay restaurantes ni nuestros famosos guachinches, donde no se hagan!!!. Trozos de costillas de cerdo con piñas (mazorcas de maíz), papas medianas y mojo de cilantro"
    private val ESCALDON_CAN = "El escaldón es una antigua receta tradicional de la isla de Tenerife, su principal ingrediente es el característico gofio. Receta antigua y tradicional de Tenerife, el escaldón de gofio o gofio escaldado se consigue hirviendo un caldo de carne o pescado que posteriormente se vierte sobre el gofio en un recipiente de barro (lebrillo) y se amasa. El gofio es uno de los productos más emblemáticos dentro de la dieta del isleño, creado a base de cereales tostados y molidos."
    private val PAPAS_ARRUGA = "Se denomina papas arrugadas a un modo de cocinar las papas, característico de las islas Canarias. Se usa principalmente un tipo de papa en concreto, la papa bonita, aunque pueden utilizarse otros tipos existentes en las Islas Canarias. En 2016, las papas arrugadas fueron proclamadas maravilla gastronómica de España en un concurso promovido por Allianz Global Assistance, consiguiendo el primer puesto mediante voto popular a través de internet."
    private val ROPA_VIEJA_C = "La ropa vieja, carne desmechada, carne desmenuzada o carne mechada es una preparación a base de carne deshebrada, específicamente de la falda de la vaca y acompañado de garbanzos."
    private val SOPA_DE_PESC = "La sopa de pescado es una preparación culinaria elaborada principalmente con trozos de pescado que suele ser cocido en un medio líquido en presencia de otros ingredientes, como pueden ser verduras."

    private var dishes: MutableList<Dish> = mutableListOf(
            Dish("Sopa de pescado", SOPA_DE_PESC, 12.0, R.drawable.sopapescado, listOf(
                    Allergen.gluten,
                    Allergen.fish,
                    Allergen.shellfish,
                    Allergen.soy
            )),
            Dish("Carne de fiesta", CARNE_FIESTA, 10.0, R.drawable.carnefiesta, listOf(
                    Allergen.gluten
            )),
            Dish("Costillas saladas", COSTILLAS_SA, 15.0, R.drawable.costillas, listOf(
                    Allergen.gluten,
                    Allergen.nuts
            )),
            Dish("Escaldon", ESCALDON_CAN, 8.0, R.drawable.escaldon, listOf(
                    Allergen.gluten,
                    Allergen.nuts
            )),
            Dish("Papas arrugadas con mojo rojo y bacalao", PAPAS_ARRUGA, 16.0, R.drawable.papasarrugadas, listOf(
                    Allergen.gluten,
                    Allergen.nuts,
                    Allergen.fish
            )),
            Dish("Ropa vieja", ROPA_VIEJA_C, 9.0, R.drawable.ropavieja, listOf(
                    Allergen.gluten,
                    Allergen.eggs,
                    Allergen.nuts
            ))
    )

    val count
        get() = dishes.size

    fun toArray() = dishes.toTypedArray()

    operator fun get(i: Int) = dishes[i]

    // Para poder iterar como si fuera una lista
    operator fun iterator() = dishes.iterator()

    operator fun plus(dish: Dish) {
        dishes.add(dish)
    }
}
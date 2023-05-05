fun main() {

    //println(naruto.attack.keys.elementAt(0))
    //println(naruto.attack.values.elementAt(0))


    //sakura.showSelection()

    /*var counter = 0
    characterComputer = sakura
    do {
        println(selectionComputer)
        attackComputer()
        counter++
    } while (counter < 4)

     */

    //selectionUserString = "sexy Jutsu"
    //println(naruto.ninjutsu[selectionUserString])

    //nurso("sexy Jutsu", sasuke)
    //println(sasuke.lifePoints)

    sakura.coloredBar()
}
    fun nurso(input: String, enemy: Character){

        if (input != "Baumstamm") {

            var counter = 0

            for (attack in naruto.attack.keys) {
                if (input == attack) {
                    enemy.lifePoints -= naruto.attack.values.elementAt(counter)
                    break
                }
                counter++
            }

            counter = 0
            for (attack in naruto.ninjutsu.keys) {
                if (input == attack) {
                    enemy.lifePoints -= naruto.attack.values.elementAt(counter)
                    break
                }
                counter++
            }

            counter = 0
            for (attack in naruto.weapon.keys) {
                if (input == attack) {
                    enemy.lifePoints -= naruto.attack.values.elementAt(counter)
                    break
                }
                counter++
            }
        }
    }




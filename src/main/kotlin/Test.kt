fun main() {

    //println(naruto.attack.keys.elementAt(0))
    //println(naruto.attack.values.elementAt(0))


    //sakura.showSelection()

    /*
    var counter = 0
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

    /*
    sakura.coloredBar()
    println("Hallo")
    println("Du da")

     */

    characterUser = sakura
    characterComputer = naruto
    selectionUserString = "Kunai"
    selectionComputer = "Taijutzu"

    var counter = 0

    do {
        lostLifePoints(selectionUserString, selectionComputer, characterUser, characterComputer)
        defensePrint()
        wichAttackUserPrint()
        whichAttackComputerPrint()
        valueOfCharacterPrint()
        selectionUserString = "große Sakura"
        selectionComputer = "sexy Jutzu"
        counter++
    } while (counter <= 5)

}






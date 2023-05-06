fun fight() {}

// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1) angreifen oder (2) ausweichen? Gib die jeweilige Zahl ein: ")
        selectionUserInt = readln().toInt()
        if (selectionUserInt == 1) {
            characterUser.showSelection()
            characterUser.lostLifePoints(selectionUserString, characterComputer)
            /*
            if (characterUser is CharacterWithGenjutsu) {
               characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)

            } else if (characterUser is CharacterWithMedicalSkills) {
                characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)
            } else {
                characterUser.showSelection()
                characterUser.lostLifePoints(selectionUserString, characterComputer)

             */
            }
        } else if (selectionUserInt == 2) {
            characterUser.baumstamm("user")
            characterUser.lostLifePoints(selectionUserString, characterComputer)
        }
}

// diese Funktion wählt ein Angriff oder Verteidigung per Zufall aus je nachdem welchen Charakter der Computer hat
// und speichert die Auswahl dan in der Variablen selectionComputer in der Main
fun attackComputer(){

    val attackList = mutableListOf<String>()

    if (characterComputer is CharacterWithGenjutsu) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Genjutsu")
    } else if (characterComputer is CharacterWithMedicalSkills) {
        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
        attackList.add("Heilung")
    } else {

        for (attack in characterComputer.attack)
            attackList.add(attack.key)

        for (attack in characterComputer.ninjutsu)
            attackList.add(attack.key)

        for (attack in characterComputer.weapon)
            attackList.add(attack.key)

        attackList.add("Baumstamm")
    }

    selectionComputer = attackList.random()

    if (selectionComputer in characterComputer.attack.keys){
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer in characterComputer.ninjutsu.keys){
        characterComputer.attackWithNinjutsu(selectionComputer, 0)
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer in characterComputer.weapon.keys) {
        characterComputer.lostLifePoints(selectionComputer, characterUser)
    } else if (selectionComputer == "Genjutsu") {
        (characterComputer as CharacterWithGenjutsu).attackWithGenjutsu(characterUser)
    } else if (selectionComputer == "Heilung") {
        (characterComputer as CharacterWithMedicalSkills).heal(selectionComputer)
    } else if (selectionComputer == "Baumstamm") {
        characterComputer.baumstamm("com")

    }
}

// diese Funktion zeigt die Spielerdaten in einer Printline an
fun valueOfCharacterPrint(){

    println("""
        Spieler 1:                                                                           Spieler 2:
                       $magenta$nameUser ${white}                                                       ${blue}Computer $white
        Charakter:     $magenta${characterUser.name} ${white}                                           $blue${characterComputer.name} $white
        Lebenspunkte:  $magenta${characterUser.lifePoints} $white/ ${characterUser.lifePointStart}      $blue${characterComputer.lifePoints} $white/ ${characterComputer.lifePointStart}
        Chakra:        $magenta${characterUser.chakra} $white/ ${characterUser.chakraStart}             $blue${characterComputer.chakra} $white/ ${characterComputer.chakraStart}
        Baumstamm:     $magenta${characterUser.baumstamm} $white/ 5                                     $blue${characterComputer.baumstamm} $white/ 5
    -------------------------------------------------------------------------------------------------------------------------------------------------------
    """.trimIndent())
}

// diese Funktion gibt einen entsprechenden Text aus bei Nutzung der Abwehr
fun defensePrint() {

    if (selectionComputer == "Baumstamm") {
            if (selectionUserString == "Baumstamm") {
                println("\nDer Computer ist ebenfalls ausgewichen")
            } else {
                println("\nDu wolltest mit $selectionUserString angreifen aber der Computer ist ausgewichen.")
            }
    } else {
        if (selectionUserString == "Baumstamm") {
            println("\nDer Computer wollte mit $selectionComputer angreifen aber du bist ausgewichen. \uD83D\uDE1D")
    }
    }
}

fun grafikForAttack(){

        println("""
 
🌀 🔥 💣 
        """.trimIndent())
}

// diese Funktion sagt dem Spieler, ob er gewonnen oder verloren hat
fun winOrLosePrint(){

    if (characterComputer.lifePoints == 0) {
        println("\n\uD83C\uDF87 Super! Du hast gewonnen. \uD83C\uDF87")
        println("""
                 (((  
                (o o)            
            ooO--(_)--Ooo
        """.trimIndent())
    } else if (characterUser.lifePoints == 0){
        println("\n\uD83D\uDE14 Schade! Du hast leider verloren \uD83D\uDE14")
        println("""
               !!!                   
              `  /_\  '
             -  (OXO)  -
            ooO--(_)--Ooo
        """.trimIndent())
    }
}

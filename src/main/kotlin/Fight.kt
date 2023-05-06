fun fight() {}

// diese Funktion fragt den Spieler, ob er angreifen oder ausweichen möchte und ruft je nach Antwort die dazugehörigen Funktionen auf
fun selectionAttackUser() {

    if (characterUser.name.isNotEmpty()) {
        print("\nMöchtest du (1) angreifen oder (2) ausweichen? Gib die jeweilige Zahl ein: ")
        selectionUserInt = readln().toInt()
        if (selectionUserInt == 1) {
            characterUser.showSelection()
            characterUser.lostLifePoints(selectionUserString, characterComputer)
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
        
    -------------------------------------------------------------------------------------------------------------------------------------------------------    
        Spieler 1:     $magenta$nameUser ${white}     |       Spieler 2:     ${blue}Computer $white
    
        Charakter:     $magenta${characterUser.name} ${white}     |     Charakter:     $blue${characterComputer.name} $white
        Lebenspunkte:  $magenta${characterUser.lifePoints} $white/ ${characterUser.lifePointStart}     Lebenspunkte:  $blue${characterComputer.lifePoints} $white/ ${characterComputer.lifePointStart}
        Chakra:        $magenta${characterUser.chakra} $white/ ${characterUser.chakraStart}     |     Chakra:        $blue${characterComputer.chakra} $white/ ${characterComputer.chakraStart}
        Baumstamm:     $magenta${characterUser.baumstamm} $white/ 5     |     Baumstamm:     $blue${characterComputer.baumstamm} $white/ 5
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

fun whichAttackComputerPrint(){

    if (selectionComputer != "Baumstamm" && selectionComputer != "Heilung" && selectionUserString != "Baumstamm") {
        println("\nDer Computer hat mit $selectionComputer angegriffen und dich getroffen.")
    }
}

fun grafikForAttack(){

    if (selectionUserString.lowercase().contains("feuer"))
        println("\n        \uD83D\uDD25 \n")

    if (selectionUserString.lowercase().contains("bombe"))
        println("\n        \uD83D\uDCA3 \n")

    if (selectionUserString.lowercase().contains("baumstamm"))
        println("\n        \uD83E\uDEB5 \n")

    if (selectionUserString.lowercase().contains("blüte"))
        println("\n         \uD83C\uDF38 \n")

    if (selectionUserString.lowercase().contains("wind"))
        println("\n         \uD83C\uDF2A\uFE0F \n")

    if (selectionUserString.lowercase().contains("schwert"))
        println("\n         \uD83D\uDDE1\uFE0F \n")

    if (selectionUserString.lowercase().contains("wasser"))
        println("\n         \uD83C\uDF0A \n")

    if (selectionUserString.lowercase().contains("taijutsu"))
        println("\n         \uD83D\uDC4A\uD83C\uDFFC \n")
}

// diese Funktion sagt dem Spieler, ob er gewonnen oder verloren hat
fun winOrLosePrint(){

    if (characterComputer.lifePoints == 0) {
        println("\nDer Computer ist gefallen und steht nicht mehr auf.")
        Thread.sleep(2000)
        println("\n      \uD83C\uDFC6 \n\uD83C\uDF87 Super! Du hast gewonnen. \uD83C\uDF87")
        println("""
            
                 (((  
                (o o)            
            ooO--(_)--Ooo
            
        """.trimIndent())
    } else if (characterUser.lifePoints == 0){
        println("\n Du bist gefallen und stehst nicht mehr auf.")
        Thread.sleep(2000)
        println("\n\uD83D\uDE14 Schade! Du hast leider verloren \uD83D\uDE14")
        println("""
               !!!                   
              `  /_\  '
             -  (OXO)  -
            ooO--(_)--Ooo
            
        """.trimIndent())
    }
}

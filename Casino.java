import java.util.Random;
import java.util.Scanner;

public class Casino {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();


        int penize = 1000;
        int volbahry = 0;


        System.out.println("=========================================");
        System.out.println("             CASINO Arabská              ");
        System.out.println("=========================================");


        boolean vcasinu = true;

        while (penize > 0 && vcasinu) {

            System.out.println();
            System.out.println("--- HLAVNÍ MENU ---");
            System.out.println("Zůstatek: " + penize + " Kč");
            System.out.println("1 - Ruleta");
            System.out.println("2 - Blackjack");
            System.out.println("3 - Odejít z casina");
            System.out.print("Vyber si hru: ");

            volbahry = scanner.nextInt();


            if (volbahry == 1) {

                boolean hraruletu = true;

                while (hraruletu && penize > 0) {

                    System.out.println();
                    System.out.println("--- RULETA ---");
                    System.out.print("Kolik chceš vsadit? ");
                    int sazka = scanner.nextInt();


                    if (sazka <= 0 || sazka > penize) {
                        System.out.println("Nedostatek Peněz");
                    } else {
                        penize = penize - sazka;


                        int typsazky = 0;
                        while (typsazky != 1 && typsazky != 2) {
                            System.out.println();
                            System.out.println("Vyber typ sázky?");
                            System.out.println("1 - Přesné číslo (Výhra 35x)");
                            System.out.println("2 - Barva (Červená / Černá / Zelená)");
                            System.out.print("Volba: ");
                            typsazky = scanner.nextInt();
                        }


                        int hadanecislo = -1;
                        int volbabarvy = -1;

                        if (typsazky == 1) {

                            while (hadanecislo < 0 || hadanecislo > 36) {
                                System.out.print("Jaké číslo (0-36)? ");
                                hadanecislo = scanner.nextInt();
                            }

                        } else if (typsazky == 2) {

                            while (volbabarvy != 1 && volbabarvy != 2 && volbabarvy != 3) {
                                System.out.print("1 - Červená, 2 - Černá, 3 - Zelená: ");
                                volbabarvy = scanner.nextInt();
                            }

                        }


                        System.out.println();
                        System.out.println("Kulička se točí...");
                        try {
                            Thread.sleep(1500);
                        } catch (Exception e) {
                        }


                        int vyhernicislo = random.nextInt(37);
                        System.out.println();
                        System.out.println("Padlo číslo: " + vyhernicislo);


                        if (typsazky == 1) {

                            if (hadanecislo == vyhernicislo) {
                                System.out.println("Vyhráváš.");
                                penize = penize + (sazka * 36);
                            } else {
                                System.out.println("Prohra.");
                            }

                        } else if (typsazky == 2) {

                            if (vyhernicislo == 0 && volbabarvy == 3) {
                                System.out.println("Zelená, Vyhráváš.");
                                penize = penize + (sazka * 36);
                            } else if (vyhernicislo != 0 && vyhernicislo % 2 != 0 && volbabarvy == 1) {
                                System.out.println("Červená, Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (vyhernicislo != 0 && vyhernicislo % 2 == 0 && volbabarvy == 2) {
                                System.out.println("Černá, Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else {
                                System.out.println("Prohráváš.");
                            }

                        }
                    }


                    if (penize > 0) {
                        System.out.println();
                        System.out.println("Aktuální zůstatek: " + penize + " Kč");
                        System.out.print("Chceš hrát další kolo? (1 - Ano, 2 - Zpět do menu): ");
                        int pokracovat = scanner.nextInt();
                        if (pokracovat != 1) {
                            hraruletu = false;
                        }
                    }

                }


            } else if (volbahry == 2) {

                boolean hrablackjack = true;

                while (hrablackjack && penize > 0) {

                    System.out.println();
                    System.out.println("--- BLACKJACK ---");
                    System.out.print("Kolik chces vsadit? ");
                    int sazka = scanner.nextInt();


                    if (sazka <= 0 || sazka > penize) {
                        System.out.println("Neplatná sázka");
                    } else {
                        penize = penize - sazka;

                        int hrackarta1 = random.nextInt(10) + 2;
                        int hrackarta2 = random.nextInt(10) + 2;
                        int hrackarty = hrackarta1 + hrackarta2;
                        String hrackartytext = hrackarta1 + ", " + hrackarta2;

                        int dealerkarta1 = random.nextInt(10) + 2;
                        int dealerkarta2 = random.nextInt(10) + 2;
                        int dealerkarty = dealerkarta1 + dealerkarta2;


                        System.out.println();
                        System.out.println("Rozdávám karty...");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }

                        System.out.println("Tvoje karty: " + hrackartytext + " (Součet: " + hrackarty + ")");
                        System.out.println("Dealer má: " + dealerkarta1 + " a ?");


                        boolean hractaha = true;

                        while (hractaha) {

                            if (hrackarty > 21) {
                                System.out.println();
                                System.out.println("Máš přes 21. Prohráváš");
                                hractaha = false;
                            } else if (hrackarty == 21) {
                                System.out.println();
                                System.out.println("Blackjack!");
                                hractaha = false;
                            } else {
                                System.out.println();
                                System.out.print("Hit nebo Stand? (1 - Hit, 2 - Stand): ");
                                int dalsi = scanner.nextInt();

                                if (dalsi == 1) {
                                    int novakarta = random.nextInt(10) + 2;
                                    hrackarty = hrackarty + novakarta;
                                    hrackartytext = hrackartytext + ", " + novakarta;

                                    System.out.println("Dostals kartu v hodnotě: " + novakarta);
                                    try {
                                        Thread.sleep(500);
                                    } catch (Exception e) {
                                    }
                                    System.out.println("Tvoje karty teď jsou: " + hrackartytext + " (Součet: " + hrackarty + ")");
                                } else {
                                    hractaha = false;
                                }
                            }
                        }


                        if (hrackarty <= 21) {

                            System.out.println();
                            System.out.println("Na řadě je dealer...");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }

                            System.out.println("Dealer odkrývá skrytou kartu: " + dealerkarta2);
                            System.out.println("Dealer má karty: " + dealerkarta1 + ", " + dealerkarta2 + " (Součet: " + dealerkarty + ")");
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {
                            }

                            while (dealerkarty < 17) {
                                int novakartadealera = random.nextInt(10) + 2;
                                dealerkarty = dealerkarty + novakartadealera;
                                System.out.println();
                                System.out.println("Dealer si vytáhl kartu v hodnotě: " + novakartadealera);
                                System.out.println("Jeho součet je teď: " + dealerkarty);
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                            }


                            System.out.println();
                            if (dealerkarty > 21) {
                                System.out.println("Dealer přetáhl! Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (hrackarty > dealerkarty) {
                                System.out.println("Máš více než dealer. Vyhráváš.");
                                penize = penize + (sazka * 2);
                            } else if (hrackarty == dealerkarty) {
                                System.out.println("Remíza. Peníze se ti vrací.");
                                penize = penize + sazka;
                            } else {
                                System.out.println("Dealer má víc. Prohráváš.");
                            }
                        }
                    }


                    if (penize > 0) {
                        System.out.println();
                        System.out.println("Aktuální zůstatek: " + penize + " Kč");
                        System.out.print("Chceš hrát další kolo? (1 - Ano, 2 - Zpět do menu): ");
                        int pokracovat = scanner.nextInt();
                        if (pokracovat != 1) {
                            hrablackjack = false;
                        }
                    }

                }


            } else if (volbahry == 3) {
                vcasinu = false;
            } else {
                System.out.println();
                System.out.println("Chybná Volba.");
            }

        }


        System.out.println();
        if (penize <= 0) {
            System.out.println("Prohrál jsi všechny peníze. Casino Arabská tě oškubalo!");
        } else {
            System.out.println("Finální zůstatek: " + penize + " Kč.");
        }

    }
}
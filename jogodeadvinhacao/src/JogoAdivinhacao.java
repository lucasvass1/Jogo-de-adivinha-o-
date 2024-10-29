import java.util.Random;
import java.util.Scanner;

public class JogoAdivinhacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário
        boolean jogarNovamente; // Variável para controlar se o jogador quer reiniciar o jogo

        do {
            // Cria uma instância de Random para gerar o número secreto
            Random random = new Random();

            // Exibe mensagem de boas-vindas e solicita o intervalo
            System.out.println("Bem-vindo ao Jogo de Adivinhação de Números!");
            System.out.print("Digite o valor mínimo do intervalo (0 a 1000): ");
            int minIntervalo = obterNumeroValido(scanner, 0, 1000); // Recebe o valor mínimo do intervalo
            System.out.print("Digite o valor máximo do intervalo (0 a 1000): ");
            int maxIntervalo = obterNumeroValido(scanner, minIntervalo + 1, 1000); // Recebe o valor máximo do intervalo, garantindo que seja maior que o mínimo

            // Gera o número secreto no intervalo definido pelo jogador
            int numeroSecreto = random.nextInt(maxIntervalo - minIntervalo) + minIntervalo;
            int tentativas = 0; // Contador de tentativas do jogador
            boolean acertou = false; // Variável para verificar se o jogador acertou o número

            // Inicia o jogo e explica as instruções
            System.out.println("\nO jogo começou! Tente adivinhar o número entre " + minIntervalo + " e " + maxIntervalo + ".");
            System.out.println("Digite 'sair' a qualquer momento para encerrar o jogo.");

            // Loop principal do jogo: continua até que o jogador acerte ou deseje encerrar
            while (!acertou) {
                System.out.print("Digite sua tentativa: ");
                String entrada = scanner.next();

                // Se o jogador digitar "sair", o jogo é encerrado
                if (entrada.equalsIgnoreCase("sair")) {
                    System.out.println("Jogo encerrado. Você tentou " + tentativas + " vez(es).");
                    acertou = true; // Atualiza para sair do loop
                    break;
                }

                // Tenta converter a entrada para um número e verifica se está no intervalo
                try {
                    int palpite = Integer.parseInt(entrada);

                    // Valida se o palpite está dentro do intervalo
                    if (palpite < minIntervalo || palpite >= maxIntervalo) {
                        System.out.println("Por favor, insira um número dentro do intervalo especificado.");
                        continue; // Volta ao início do loop sem contar a tentativa
                    }

                    tentativas++; // Incrementa o contador de tentativas

                    // Verifica se o palpite é o número secreto
                    if (palpite == numeroSecreto) {
                        System.out.println("Parabéns! Você acertou o número em " + tentativas + " tentativa(s).");
                        acertou = true; // Sinaliza que o jogador acertou
                    } else if (palpite < numeroSecreto) {
                        System.out.println("O número é maior que " + palpite + ". Tente novamente.");
                    } else {
                        System.out.println("O número é menor que " + palpite + ". Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    // Exceção para entrada inválida (não numérica)
                    System.out.println("Entrada inválida. Por favor, digite um número ou 'sair' para encerrar o jogo.");
                }
            }

            // Após o jogo, pergunta se o jogador deseja jogar novamente
            System.out.print("\nDeseja jogar novamente? (sim/não): ");
            String resposta = scanner.next();
            jogarNovamente = resposta.equalsIgnoreCase("sim"); // Reinicia o jogo se o jogador responder "sim"
        } while (jogarNovamente); // O loop externo mantém o jogo ativo enquanto o jogador quiser

        scanner.close(); // Fecha o Scanner ao encerrar o jogo
        System.out.println("Obrigado por jogar!"); // Mensagem final de despedida
    }

    // Método para garantir que o usuário insira um número válido dentro de um intervalo
    private static int obterNumeroValido(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int numero = Integer.parseInt(scanner.next());
                // Verifica se o número está dentro do intervalo especificado
                if (numero < min || numero > max) {
                    System.out.println("Por favor, insira um número entre " + min + " e " + max + ".");
                } else {
                    return numero; // Retorna o número se ele for válido
                }
            } catch (NumberFormatException e) {
                // Exibe uma mensagem caso a entrada não seja um número
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }
}

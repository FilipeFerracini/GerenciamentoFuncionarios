# GerenciamentoFuncionarios

No presente trabalho iremos elaborar um sistema básico de gerenciamento de funcionários. Este sistema contará com cadastro, busca e remoção de funcionários. Os funcionários poderão ser de uma das 3 classes a seguir:
1.	Funcionário Celetista;
2.	Funcionário PJ, ou;
3.	Funcionário diarista.
A empresa que solicita que o sistema emita um relatório contendo os salários pagos aos funcionários (bruto e líquido) semanalmente. Aos funcionários celetistas o desconto em folha é de 15,5% (7,5% INSS e 8% FGTS). Diaristas e PJ não sofrem descontos de pagamento. Os salários brutos pagos pela empresa são:
1.	CLT: R$ 375,00/semana
2.	PJ: R$ 500.00/semana
3.	Diarista: R$ 65,00/dia
Adicionalmente, a empresa trabalha com sistema de escalas semanal para seus funcionários CLT e PJ, onde o funcionário trabalha em escala 2:1 (trabalha por 2 dias consecutivos e recebe uma folga, no dia que segue) e 4:1, respectivamente. Os funcionários PJ e CLT trabalham apenas de segunda a sexta. Os diaristas, por sua vez, trabalham sempre que os celetistas ou os PJ estão de folga, inclusive de finais de semana e feriados. Finalmente, os funcionários de todas as categorias ganham folga remunerada em seu aniversário.
A empresa emite um relatório da escala semanal, portanto o sistema deve automatizar e fornecer este relatório na semana em vigência sempre que requerido, informando alguns dados, como aniversário de funcionários e feriados

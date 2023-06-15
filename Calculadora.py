def calcular_nota_final():
    notas = []
    pesos = []

    for i in range(1, 7):
        nota = entry_notas[i].get()
        peso = entry_pesos[i].get()

        if nota and peso:
            notas.append(float(nota))
            pesos.append(float(peso))

    nota_ponderada = [nota * peso for nota, peso in zip(notas, pesos)]
    
    if nota_ponderada:
        nota_final = sum(nota_ponderada) / sum(pesos)
        label_resultado["text"] = f"A nota final do semestre é: {nota_final:.2f}"
    else:
        label_resultado["text"] = "Preencha pelo menos um campo de nota e peso."

import tkinter as tk

window = tk.Tk()
window.title("Calculadora de Nota Final")

entry_notas = {}
entry_pesos = {}

for i in range(1, 7):
    label_nota = tk.Label(window, text=f"Nota {i}:")
    label_nota.pack()
    entry_notas[i] = tk.Entry(window)
    entry_notas[i].pack()

    label_peso = tk.Label(window, text=f"Peso da Nota {i} (em decimal):")
    label_peso.pack()
    entry_pesos[i] = tk.Entry(window)
    entry_pesos[i].pack()

button_calcular = tk.Button(window, text="Calcular", command=calcular_nota_final)
button_calcular.pack()

label_resultado = tk.Label(window, text="")
label_resultado.pack()

window.mainloop()

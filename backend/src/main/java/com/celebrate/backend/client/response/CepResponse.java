package com.celebrate.backend.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// {
//     "cep": "01001-000",
//     "logradouro": "Praça da Sé",
//     "complemento": "lado ímpar",
//     "unidade": "",
//     "bairro": "Sé",
//     "localidade": "São Paulo",
//     "uf": "SP",
//     "estado": "São Paulo",
//     "regiao": "Sudeste",
//     "ibge": "3550308",
//     "gia": "1004",
//     "ddd": "11",
//     "siafi": "7107"
//   }

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CepResponse {
    
    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;
    public String estado;
}

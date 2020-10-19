package com.lucas.ibgereport.dtos.ibge;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lucas.ibgereport.dtos.deserializers.IReportDeserializer;
import lombok.Data;

@Data
@JsonDeserialize(using = IReportDeserializer.class)
public class ReportDTO {
    Long idEstado;
    String siglaEstado;
    String regiaoNome;
    String nomeCidade;
    String nomeFormatado;

    public ReportDTO(){}

    public ReportDTO(Long idEstado, String siglaEstado, String regiaoNome, String nomeCidade) {
        this.idEstado = idEstado;
        this.siglaEstado = siglaEstado;
        this.regiaoNome = regiaoNome;
        this.nomeCidade = nomeCidade;
        this.nomeFormatado = nomeCidade + "/" + siglaEstado;
    }

    public ReportDTO(Long idEstado, String siglaEstado, String regiaoNome, String nomeCidade, String nomeFormatado) {
        this.idEstado = idEstado;
        this.siglaEstado = siglaEstado;
        this.regiaoNome = regiaoNome;
        this.nomeCidade = nomeCidade;
        this.nomeFormatado = nomeFormatado;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeFormatado() {
        return nomeCidade + "/" + siglaEstado;
    }

    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }
}

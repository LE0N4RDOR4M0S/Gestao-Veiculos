package com.example.demo.domain;

import java.io.IOException; 
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
@Entity
public class Parada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length=100,nullable = false )
	String endereco;
	private Double latitude;
    private Double longitude;
	@JsonIgnore
	@OneToMany(mappedBy = "parada", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Estudante> estudante = new HashSet<Estudante>();
	@JsonIgnore
	@OneToMany(mappedBy = "parada", cascade = CascadeType.ALL)
	private Set<Itinerario> itinerario = new HashSet<Itinerario>();
	
	public Parada(Integer id, String endereco) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.buscarCoordenadas();
	}
	
	public Parada(String endereco, Double latitude, Double longitude) {
	    this.endereco = endereco;
	    this.latitude = latitude;
	    this.longitude = longitude;
	}


	public Parada() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

	public Set<Estudante> getEstudante() {
		return estudante;
	}

	public void setEstudante(Set<Estudante> estudante) {
		this.estudante = estudante;
	}

	public Set<Itinerario> getItinerario() {
		return itinerario;
	}

	public void setItinerario(Set<Itinerario> itinerario) {
		this.itinerario = itinerario;
	}
	
	// Método para buscar as coordenadas do Google Maps com base no endereço
    private void buscarCoordenadas() {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("SUA_CHAVE_DE_API").build();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, endereco).await();
            if (results.length > 0) {
                latitude = results[0].geometry.location.lat;
                longitude = results[0].geometry.location.lng;
            }
        } catch (IOException | InterruptedException | com.google.maps.errors.ApiException e) {
            // Lide com exceções de geocodificação, se necessário
            e.printStackTrace();
        }
    }
	
	
}

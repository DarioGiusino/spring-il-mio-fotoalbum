package org.java.exalbum.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Compila questo campo")
	@Size(min = 3, max = 25, message = "The title must be 3-25 char.")
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	private String url;

	private Boolean visible;

	@ManyToMany
	@JsonManagedReference
	private List<Category> categories;

	public Photo() {
	}

	public Photo(String title, String description, String url, Boolean visible) {
		setTitle(title);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
	}
	
	public Photo(String title, String description, String url, Boolean visible, Category... categories) {
		this(title, description, url, visible);
		setCategories(categories);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public void setCategories(Category[] categories) {
		setCategories(Arrays.asList(categories));
	}
	public void removeCategory(Category category) {
		getCategories().remove(category);		
	}

	@Override
	public String toString() {
		return "[" + getId() + "] " + getTitle() + "\nDescription: " + getDescription() + "\nPicture: " + getUrl()
				+ "\nVisible: " + (isVisible() ? "Sì" : "No");
	}
}

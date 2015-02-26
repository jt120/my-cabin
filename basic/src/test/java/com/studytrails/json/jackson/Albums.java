package com.studytrails.json.jackson;


import java.util.Arrays;

public class Albums {

	private String title;
	private Dataset[] dataset;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDataset(Dataset[] dataset) {
		this.dataset = dataset;
	}

	public String getTitle() {
		return title;
	}

	public Dataset[] getDataset() {
		return dataset;
	}

    @Override
    public String toString() {
        return "Albums{" +
                "title='" + title + '\'' +
                ", dataset=" + Arrays.toString(dataset) +
                '}';
    }
}
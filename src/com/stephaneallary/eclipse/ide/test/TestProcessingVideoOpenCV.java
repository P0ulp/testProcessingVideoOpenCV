package com.stephaneallary.eclipse.ide.test;

import processing.core.*;
import processing.video.*;
import gab.opencv.*;

import java.awt.Rectangle;

public class TestProcessingVideoOpenCV extends PApplet {

	private static final long serialVersionUID = 1L;
	
	private Capture video;
	private OpenCV opencv;

	public static void main(String[] args) {
		PApplet.main( new String[] {"com.stephaneallary.eclipse.ide.test.TestProcessingVideoOpenCV"});
	}
	
	public void setup() {
		size(640, 480);
		video = new Capture(this, 640/2, 480/2);
		opencv = new OpenCV(this, 640/2, 480/2);
		opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);  
		video.start();
		System.out.println("PATH : " + System.getProperty("java.library.path"));
	}
	
	public void draw() {
		this.frame.setTitle(frameRate+"");
		if (this.video.available()) {
		    this.video.read();
			opencv.loadImage(video);
		}
		scale(2);
		image(video, 0, 0 );
		noFill();
		stroke(0, 255, 0);
	    strokeWeight(3);

		Rectangle[] faces = opencv.detect();
		
		for (int i = 0; i < faces.length; i++) {
			rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
		}
	}


}

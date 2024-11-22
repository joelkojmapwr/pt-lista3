/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.composite.model;

import java.util.List;
import java.util.ArrayList;


/**
 * @author Joao Pereira
 * 
 */
public abstract class CompositeShape extends Shape {

	List<Shape> shapes;

	public CompositeShape() {
		this.shapes = new ArrayList<Shape>();
	}

	/**
	 * Remove a shape from this shape childrens
	 * 
	 * @param shape
	 *            the shape to remove
	 * @return true if the shape was present and was removed, false if the shape
	 *         was not present
	 */
	public boolean removeShape(Shape shape) {
		for (Shape currentShape : shapes) {
			if (currentShape.equals(shape)) {
				shapes.remove(shape);
				return true;
			}
		}
		return false;

	}

	/**
	 * Return the total shapes count in case this is a composite
	 * 
	 * @return the total count of shapes if the shape is composite. -1 otherwise
	 */
	public int getShapeCount() {
		if (this instanceof CompositeShape == false) {
			return -1;
		}
		int countShapes=0;
		for (Shape shape : shapes) {
			if (shape instanceof CompositeShape) {
				countShapes+=shape.asComposite().getShapeCount();
			}
			countShapes++;
		}
		return countShapes;

	}

	/**
	 * Add a shape to this shape.
	 * 
	 * @param shape
	 *            The shape to add
	 * @throws ShapeDoesNotSupportChildren
	 *             if this shape is not a composite
	 */
	public void addShape(Shape shape) throws ShapeDoesNotSupportChildren {
		if (this instanceof CompositeShape == false) {
			throw new ShapeDoesNotSupportChildren();
		}
		shapes.add(shape);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	/**
	 * @param circle
	 * @return
	 */
	public List<Shape> getShapesByType(ShapeType circle) {
		List<Shape> shapesByType = new ArrayList<Shape>();
		for (Shape shape : shapes) {
			if (shape.getType().equals(circle)) {
				shapesByType.add(shape);
			}
		}
		return shapesByType;
	}

	/**
	 * Return all shapes that are leafs in the tree
	 * 
	 * @return
	 */
	public List<Shape> getLeafShapes() {
		List<Shape> leafs = new ArrayList<Shape>();
		for (Shape shape : shapes) {
			if (shape instanceof CompositeShape == false) {
				leafs.add(shape);
			} else if (shape.asComposite().getShapeCount() == 0) {
				// current shape is composite but doesn't have children so it's a leaf
				leafs.add(shape);
			} else {
				leafs.addAll(shape.asComposite().getLeafShapes());
			}
		}
		return leafs;
	}

	@Override
	public void setX(int x) {
		super.setX(x);
		for (Shape shape : shapes) {
			shape.setX(x);
		}
	}

	@Override 
	public void setY(int y) {
		super.setY(y);
		for (Shape shape : shapes) {
			shape.setY(y);
		}
	}

	@Override
	public void move(int xIncrement, int yIncrement) {
		super.move(xIncrement, yIncrement);
		for (Shape shape : shapes) {
			shape.move(xIncrement, yIncrement);
		}
	}

	/*/**
	 * Factory method for the list of children of this shape
	 * 
	 * @return
	 
	protected List<Shape> createShapesList() {
		return null;
		// TODO: Implement
	}*/
}

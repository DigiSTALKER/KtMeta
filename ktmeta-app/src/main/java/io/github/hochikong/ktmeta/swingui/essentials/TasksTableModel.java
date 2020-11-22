/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.hochikong.ktmeta.swingui.essentials;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ckhoi
 */
public class TasksTableModel extends AbstractTableModel{
    private final String[] columnNames = {
        "Task Name",
        "Task Status",
        "Task UUID"
    };
    
    private Object[][] data = {
            {"Task 1", "Running", "UUID 1"},
            {"Task 2", "Waiting", "UUID 2"},
    };
    
    public TasksTableModel(Object[][] customData){
        this.data = customData;
    }
    
    public TasksTableModel(){}

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    // my implementation

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    public Object[][] readData(){
        return this.data;
    }
}

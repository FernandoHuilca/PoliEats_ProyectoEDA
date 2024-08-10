/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

/**
 *
 * @author Fernando_Huilca
 */
public class OrdenamientoPorMergeSort {
    public OrdenamientoPorMergeSort() {
    }

    public void ordenar(int[] arreglo) {
        this.mergeSort(arreglo, 0, arreglo.length - 1);
    }

    private void mergeSort(int[] arreglo, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            this.mergeSort(arreglo, left, mid);
            this.mergeSort(arreglo, mid + 1, right);
            this.merge(arreglo, left, mid, right);
        }

    }

    private void merge(int[] arreglo, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(arreglo, left, L, 0, n1);
        System.arraycopy(arreglo, mid + 1, R, 0, n2);
        int i = 0;
        int j = 0;

        int k;
        for(k = left; i < n1 && j < n2; ++k) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                ++i;
            } else {
                arreglo[k] = R[j];
                ++j;
            }
        }

        while(i < n1) {
            arreglo[k] = L[i];
            ++i;
            ++k;
        }

        while(j < n2) {
            arreglo[k] = R[j];
            ++j;
            ++k;
        }

    }
    
    // Método para ordenar arreglos de double[]
    public void ordenar(double[] arreglo) {
        mergeSortDouble(arreglo, 0, arreglo.length - 1);
    }

    private void mergeSortDouble(double[] arreglo, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortDouble(arreglo, left, mid);
            mergeSortDouble(arreglo, mid + 1, right);
            mergeDouble(arreglo, left, mid, right);
        }
    }

    private void mergeDouble(double[] arreglo, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        double[] L = new double[n1];
        double[] R = new double[n2];
        System.arraycopy(arreglo, left, L, 0, n1);
        System.arraycopy(arreglo, mid + 1, R, 0, n2);
        int i = 0;
        int j = 0;
        int k;

        for (k = left; i < n1 && j < n2; ++k) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                ++i;
            } else {
                arreglo[k] = R[j];
                ++j;
            }
        }

        while (i < n1) {
            arreglo[k] = L[i];
            ++i;
            ++k;
        }

        while (j < n2) {
            arreglo[k] = R[j];
            ++j;
            ++k;
        }
    }
}
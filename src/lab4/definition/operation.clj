(ns lab4.definition.operation)

(defn con [expr & rest]
  (cons ::con (cons expr rest)))

(defn con? [expr]
  (= (first expr) ::con))

(defn dis [expr & rest]
  (cons ::dis (cons expr rest)))

(defn dis? [expr]
  (= (first expr) ::dis))

(defn imp [expr & rest]
  (cons ::imp (cons expr rest)))

(defn imp? [expr]
  (= (first expr) ::imp))

(defn inv [expr]
  (cons ::inv (list expr)))

(defn inv? [expr]
  (= (first expr) ::inv))

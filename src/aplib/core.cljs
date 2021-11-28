(ns aplib.core 
  (:require [applied-science.js-interop :as j]
            [promesa.core :as p]
            ["@accordproject/cicero-core"   :as cc]
            ["@accordproject/cicero-engine" :as ce]
            ["@accordproject/concerto-core" :as co]))


(def local-dir "/Users/tmb/Coding/trustblocks/cicero-template-library/src/")


(defn node-slurp [path]
  (let [fs (js/require "fs")]
    (.readFileSync fs path "utf8")))

(defn get-sample
  "Load sample md from file"
  [k]
  (node-slurp (str local-dir k "/text/sample.md")))

 (println (get-template "latedeliveryandpenalty"))

(defn get-template
  [k]
  (node-slurp (str local-dir k "/text/grammar.tem.md")))

(defn get-model
  [k]
  (node-slurp (str local-dir k "/model/clause.cto")))

(defn get-logic
  [k]
  (node-slurp (str local-dir k "/logic/logic.ergo")))

(defn get-metadata
  [k]
  (node-slurp (str local-dir k "/package.json")))

(def template (get-template "latedeliveryandpenalty"))

(def clause (get-sample "latedeliveryandpenalty"))

(def model (get-model "latedeliveryandpenalty"))




(def metadata (get-metadata "latedeliveryandpenalty" )) 
 
(def logic (get-logic "latedeliveryandpenalty" )) 

(println template) 
 
(println clause)
 
(println model)

(println metadata)

(println logic)


(def modelManager (.new co/ModelManager))

(.addModelFile model (.new ModelManager))

(.cc/parse clause_instance clause)

(comment

;;;;;;;;;;;  Cicero ;;;;;;;;;;;;;  

;;;; https://docs.accordproject.org/docs/ref-cicero-api.html 
  
;;;;; Archive handlers  ;;;;;
compositeArchiveLoader.accepts (url)
  
compositeArchiveLoader.load (url, options) ⇒ Promise
  
;;;;; Clauses ;;;;;;

Clause
  
A Clause is executable business logic, linked to a natural language (legally enforceable) 
  template. A Clause must be constructed with a template and then prior to execution the data 
  for the clause must be set. Set the data for the clause (an instance of the template model) 
  by either calling the setData method or by calling the parse method and passing in natural 
  language text that conforms to the template grammar.

Templates

templateInstance.draft ([options], [currentTime], [utcOffset]) ⇒ string
Generates the natural language text for a contract or clause clause; combining the text from the template and the instance data.


templateInstance.toJSON() ⇒ object
Returns a JSON representation of the clause

Kind: instance method of TemplateInstance
Returns: object - the JS object for serialization
  const template = await Template.fromDirectory('./test/data/latedeliveryandpenalty') ;


TemplateInstance.rebuildParser(parserManager, logicManager, ergoEngine, templateName, grammar)
Utility to rebuild a parser when the grammar changes

Kind: static method of TemplateInstance

Param	Type	Description
parserManager	*	the parser manager
logicManager	*	the logic manager
ergoEngine	*	the evaluation engine
templateName	string	this template name
grammar	string	the new grammar


template.getParserManager() ⇒ ParserManager
Provides access to the parser manager for this template. The parser manager can convert template data to and from natural language text.

Kind: instance method of Template
Returns: ParserManager - the ParserManager for this template



// load the DSL text for the template

    const testLatePenaltyInput = fs.readFileSync (path.resolve(__dirname, 'text/', 'sample.md'), 'utf8') ;

    const clause = new Clause (template) ;
    clause.parse (testLatePenaltyInput) ;

// get the JSON object created from the parse

  (println clause)  
    const data = clause.getData ();
  )

(comment

;;;;;;;;;;;;;;;;;   Concerto   ;;;;;;;;;;;;;;;  

;;;;;https://docs.accordproject.org/docs/model-api.html
  
;;; Validating JSON data using a Model
  
const ModelManager = require('@accordproject/concerto-core').ModelManager;
const Concerto = require('@accordproject/concerto-core').Concerto;
const modelManager = new ModelManager();
modelManager.addModelFile( `namespace org.acme.address
concept PostalAddress {
  o String streetAddress optional
  o String postalCode optional
  o String postOfficeBoxNumber optional
  o String addressRegion optional
  o String addressLocality optional
  o String addressCountry optional
}`, 'model.cto');

const postalAddress = {
    $class : 'org.acme.address.PostalAddress',
    streetAddress : '1 Maine Street'
};
const concerto = new Concerto(modelManager);
concerto.validate(postalAddress);


;;;; Validating an Instance ;;;;;;

const postalAddress = {
    $class : 'org.acme.address.PostalAddress',
    missing : '1 Maine Street'
};


;;;  Introspection   ;;;

const typeDeclaration = concerto.getTypeDeclaration (postalAddress);
const fqn = typeDeclaration.getFullyQualifiedName ();
console.log (fqn); // should equal 'org.acme.address.PostalAddress'

  )
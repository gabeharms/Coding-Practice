// CONSTANTS
SEARCH_REGEX = 'subject|search'

fetchAndInjectHeaderHtml("header.html")

// NODE REFERENCES
var body = document.body
var outerContainer = document.getElementById("extension-outer-container")
var extensionContainer = document.getElementById("extension-container")
var textContainer = document.getElementById("extension-text-container")
var destinationSelect = document.getElementById("destination-select")

// LOCAL VARIABLES
var found = false;

// EVENT LISTENERS

//animateHeaderSlide()
setTimeout(traverseAndHighlight, 1500)

// FUNCTION DEFINITIONs

function fetchAndInjectHeaderHtml(fileName) {
  var xmlHttp = null;
  var inject  = document.createElement("div");

  xmlHttp = new XMLHttpRequest();
  xmlHttp.open( "GET", chrome.extension.getURL (fileName), false );
  xmlHttp.send( null );
  inject.innerHTML = xmlHttp.responseText
  inject.style.width = '100%'
  inject.id = "extension-outer-container"
  inject.classList.toggle("collapsible")
  inject.classList.toggle("collapsed")

  document.body.insertBefore (inject, document.body.firstChild);
}

function traverseAndHighlight() {
  findAndReplace(SEARCH_REGEX)
  if (found) {
    extensionContainer.classList.toggle("red-header")
    textContainer.innerHTML = "FOUND MATCH"
    textContainer.classList.toggle("black")
  }
  else {
    textContainer.innerHTML = "ALL CLEAR"
  }

  outerContainer.classList.toggle('collapsed')
  extensionContainer.classList.toggle('hidden')
}

function findAndReplace(searchText, searchNode) {
  var regex = typeof searchText === 'string' ?
    new RegExp(searchText, 'g') : searchText,
    childNodes = (searchNode || document.body).childNodes,
    excludes = 'script',
    cnLength = childNodes.length;
  while (cnLength--) {
    var currentNode = childNodes[cnLength];
    if (currentNode.nodeType === 1 && (excludes + ',').indexOf(currentNode.nodeName.toLowerCase() + ',') === -1) {
      arguments.callee(searchText, currentNode);
    }
    if (!regex.test(currentNode.data) ) {
      continue;
    }
    currentNode.parentElement.classList.toggle("big-red-border")
    found = true;
  }
}
